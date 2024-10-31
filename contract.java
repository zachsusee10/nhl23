import java.util.Scanner;
import java.util.Random;


public class contract {
	private static final Random random = new Random();
	
	public static int retirementage(int age, int contractlength) {
		int retirement = 0;
		double initialprob = .05;
		if (age+contractlength>=35) {
			for(int i = 35; i<=(contractlength+age); i++) {
				double random = Math.random();
				if (random<=initialprob) {
					retirement=i;
					return retirement;
				} else {
					initialprob = initialprob+.1357;
				}
			}
			return 0;
		}
		else {
			return 0;
		}
	}
	public static Boolean retirementcheck(int age, int term) {
		double retire;
		double prob = .15*(age-35);
		if (age >= 36) {
			retire = Math.random();
			if (retire>prob) {
				return true;
			}
		}
		return false;
	}
	public static int overall(int age, int pot, int ovrpre, int points, int prepts, int assists, int preassists, int goals, int pregoals, int plusminus, int preplusminus, int pos, int pltype) {
		double ageModifier = 0;
		double weightPoints = 0, weightAssists = 0, weightGoals = 0, weightPlusMinus = 0;
	    if (age < 28) {
	        ageModifier = (28 - age) * 0.25; 
	    } else if (age > 33) {
	        ageModifier = -(age - 33) * 0.3; 
	    }

	    // Pot modifier based on pot and age
	    double potModifier = 0;
	    if (age <= 23) {
	        int potValue = pot;
	        switch (potValue) {
	            case 1:
	                potModifier = ovrpre < 94 ? (94 - ovrpre) * 0.4 : 0;
	                break;
	            case 2:
	                potModifier = ovrpre < 90 ? (90 - ovrpre) * 0.32 : 0;
	                break;
	            case 3:
	                potModifier = ovrpre < 83 ? (83 - ovrpre) * 0.24 : 0;
	                break;
	            case 4:
	                potModifier = ovrpre < 80 ? (80 - ovrpre) * 0.16 : 0;
	                break;
	            case 5:
	                potModifier = ovrpre < 75 ? (75 - ovrpre) * 0.08 : 0;
	                break;
	            default:
	                potModifier = 0;
	                break;
	        }
	    }
	    
	    // Modifier for pos and pltype
	    double performanceModifier = 0;
	    if (pos >= 1 && pos <= 3) {

	        switch (pltype) {
	            case 1:
	                weightPoints = 1.0;
	                weightAssists = 1.0;
	                weightPlusMinus = 0.8;
	                weightGoals = 0.6;
	                break;
	            case 2:
	            case 3:
	                weightPoints = 1.0;
	                weightAssists = 0.6;
	                weightPlusMinus = 0.8;
	                weightGoals = 1.0;
	                break;
	            case 4:
	                weightPoints = 1.0;
	                weightAssists = 1.0;
	                weightPlusMinus = 1.0;
	                weightGoals = 1.0;
	                break;
	            case 5:
	                weightPoints = 0.9;
	                weightAssists = 0.6;
	                weightPlusMinus = 1.2;
	                weightGoals = 0.9;
	                break;
	            default:
	                weightPoints = 0;
	                weightAssists = 0;
	                weightPlusMinus = 0;
	                weightGoals = 0;
	                break;
	        }

	        performanceModifier += (points - prepts) * weightPoints;
	        performanceModifier += (assists - preassists) * weightAssists;
	        performanceModifier += (goals - pregoals) * weightGoals;
	        performanceModifier += (plusminus - preplusminus) * weightPlusMinus;

	        // Normalize the performance modifier (this can be adjusted for tuning)
	        performanceModifier *= 0.18;
	    } else if (pos >= 4 && pos <= 5) {
	    	
        switch (pltype) {
            case 1:
                weightPoints = 0.6;
                weightAssists = 0.6;
                weightPlusMinus = 1.5;
                weightGoals = 0.6;
                break;
            case 2:
                weightPoints = 1.0;
                weightAssists = 1.0;
                weightPlusMinus = 0.8;
                weightGoals = 0.6;
                break;
            case 3:
                weightPoints = 0.8;
                weightAssists = 0.8;
                weightPlusMinus = 1.2;
                weightGoals = 0.8;
                break;
            default:
                weightPoints = 0;
                weightAssists = 0;
                weightPlusMinus = 0;
                weightGoals = 0;
                break;
        }
    }

    performanceModifier += (points - prepts) * weightPoints;
    performanceModifier += (assists - preassists) * weightAssists;
    performanceModifier += (goals - pregoals) * weightGoals;
    performanceModifier += (plusminus - preplusminus) * weightPlusMinus;
    
    double pointsCloseness = (double) points / 165 - 0.5;
    double goalsCloseness = (double) goals / 65 - 0.5;
    double assistsCloseness = (double) assists / 100 - 0.5;
    double plusminusCloseness = (double) plusminus / 60 - 0.5;

    // Apply exponential function for growth/shrink effect
    pointsCloseness = Math.pow(pointsCloseness, 3) * weightPoints;
    goalsCloseness = Math.pow(goalsCloseness, 3) * weightGoals;
    assistsCloseness = Math.pow(assistsCloseness, 3) * weightAssists;
    plusminusCloseness = Math.pow(plusminusCloseness, 3) * weightPlusMinus;

    // Compute the average closeness modifier with weights
    double avgClosenessModifier = (pointsCloseness + goalsCloseness + assistsCloseness + plusminusCloseness) / 
                                  (weightPoints + weightGoals + weightAssists + weightPlusMinus);

    double combinedPerformanceModifier = (performanceModifier + avgClosenessModifier) / 2;

    // Normalize the performance modifier
    combinedPerformanceModifier *= 0.16;

    Random rand = new Random();

    
    if (age<27 || age>33) {
    ageModifier += (rand.nextDouble() * 2 - 1) * 0.3;  
    }
    
    if (age<=23) {
    potModifier += (rand.nextDouble() * 2 - 1) * 1;
    }
   
    combinedPerformanceModifier += (rand.nextDouble() * 2 - 1) * 0.3;
    
    double stabilityFactor = 1.0;

    if (age >= 27 && age <= 33) {
        // Set stability factor to a value less than 1 to reduce the effect of modifiers
        stabilityFactor = 0.18;
    }

    // Compute the final overall
    int overall = ovrpre + (int)(stabilityFactor *(ageModifier + potModifier + combinedPerformanceModifier));
    
    if (overall > 97) {
        int excess = overall - 97;
        overall -= excess;
    }

    // Ensure the overall is within the range [45, 99]
    overall = Math.max(45, Math.min(97, overall));

	// After computing the new overall
if (overall < ovrpre && (points > prepts || assists > preassists || goals > pregoals) {
    overall = ovrpre;
}

    return overall;
}
	public static int goals(int ovr, int pregoals, int pos, int pltype) {
	    // Normalize inputs
	    double normOvr = (ovr - 50.0) / 49.0; 
	    double normPregoals = pregoals / 65.0;

	    // Introduce positional and player type multipliers
	    double posMultiplier = 1.0;
	    double pltypeMultiplier = 1.0;

	    if (pos >= 1 && pos <= 3) {
	        if (pltype == 1 || pltype == 5) {
	            pltypeMultiplier = 1.2; 
	        } else if (pltype == 2 || pltype == 3) {
	            pltypeMultiplier = 1.7;
	        }
	        posMultiplier = 1.5; 
	    } else if (pos >= 4 && pos <= 5) {
	        if (pltype == 1) {
	            pltypeMultiplier = 0.3;
	        } else if (pltype == 2) {
	            pltypeMultiplier = 0.9;
	        }
	        posMultiplier = 0.7; 
	    }

	    // Weighted average
	    double weightOvr = 0.6;
	    double weightPregoals = 0.4;
	    double avg = (normOvr * weightOvr + normPregoals * weightPregoals) * posMultiplier * pltypeMultiplier;

	    
	    Random rand = new Random();
        double randomFactor = 0.6 + 0.1 * rand.nextGaussian();
	    int result = (int) (avg * randomFactor * 65);

	    // Cap values to a maximum of 65, and occasionally let them be higher
	    if (result > 65) {
	        if (random.nextDouble() < (1.0 / 10.0)) { 
	            result = 65 + (int)(Math.random() * 6);
	        } else {
	            result = 65;
	        }
	    }

	    return result;
	}

	    
	public static int assists(int ovr, int preassists, int pos, int pltype) {
		double normOvr = (ovr - 50.0) / 50.0;
        double normPreassists = preassists / 100.0;
        
        // Introduce positional and player type multipliers
        double posMultiplier = 1.0;
        double pltypeMultiplier = 1.0;

        if (pos >= 1 && pos <= 3) {
            posMultiplier = 1.5;
            if (pltype == 1) {
                pltypeMultiplier = 1.7;
            } else if (pltype == 2 || pltype == 3 || pltype == 5) {
                pltypeMultiplier = 1.2;
            }
        } else if (pos >= 4 && pos <= 5) {
            posMultiplier = 0.9;
            if (pltype == 1) {
                pltypeMultiplier = 0.6;
            } else if (pltype == 2) {
                pltypeMultiplier = 1.6;
            }
        }

        // Weighted average
        double weightOvr = 0.6; 
        double weightPreassists = 0.4; 
        double avg = (normOvr * weightOvr + normPreassists * weightPreassists) * posMultiplier * pltypeMultiplier;
        
        Random rand = new Random();
        double randomFactor = 0.6 + 0.1 * rand.nextGaussian();
        
        // Multiplying average with random factor and scaling to 0-100 range
        int result = (int) (avg * randomFactor * 100);
        
     // Cap values to a maximum of 100, and occasionally let them be higher
        if (result > 100) {
            if (random.nextDouble() < (1.0/10.0)) {  
                result = 100 + (int)(Math.random()*6);
            } else {
                result = 100;
            }
        }

        
        return result;
    }
	

    
	public static int plusminus(int ovr, int preplusminus, int pos, int pltype, int points, int prepoints) {
	    double performanceScore = 0;
	    double improvementScore = 0;
	    Random random = new Random();

	    if (pos >= 1 && pos <= 3) {
	        if (pltype >= 1 && pltype <= 4) {
	            performanceScore = computePerformanceScore(points, ovr, 130, 90, 60, 3, 4, 4);
	        } else if (pltype == 5) {
	            performanceScore = computePerformanceScore(points, ovr, 95, 60, 30, 3, 3, 4);
	        }
	    } else if (pos >= 4 && pos <= 5) {
	        if (pltype == 1) {
	            performanceScore = computePerformanceScore(points, ovr, 65, 45, 20, 2, 2, 2);
	            double plusMinusImprovement = preplusminus * 0.2;
	            performanceScore = (performanceScore + plusMinusImprovement) / 2;
	        } else if (pltype == 2) {
	            performanceScore = computePerformanceScore(points, ovr, 105, 70, 50, 3, 3, 3);
	        } else if (pltype == 3) {
	            performanceScore = computePerformanceScore(points, ovr, 100, 65, 50, 3, 3, 3);
	        }
	    }

	    improvementScore = (points - prepoints) * 0.2;

	    double finalScore = 0.8 * performanceScore + 0.2 * improvementScore;
	    finalScore = Math.min(Math.max(finalScore, -30), 60);  // clip the value to ensure it doesn't exceed the bounds

	    
	    finalScore += random.nextInt(5) - 2; 

	    return (int) finalScore;
	}

	private static double computePerformanceScore(int points, int ovr, int highPoint, int midPoint, int lowPoint, int highDec, int midDec, int lowDec) {
	    double deltaOvr = 97 - ovr;
	    highPoint -= deltaOvr * highDec;
	    midPoint -= deltaOvr * midDec;
	    lowPoint -= deltaOvr * lowDec;

	    if (points >= highPoint) {
	        return 50;
	    } else if (points <= lowPoint) {
	        return -30;
	    } else {
	        // linear interpolation
	        if (points > midPoint) {
	            return interpolate(points, midPoint, highPoint, 0, 50);
	        } else {
	            return interpolate(points, lowPoint, midPoint, -30, 0);
	        }
	    }
	}

	private static double interpolate(double value, double low1, double high1, double low2, double high2) {
	    return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
	}
	public static Boolean EVcheck(int pts, int ovr, int plusminus, int pos, int pltype, double contractval, double salarycap) {
		int total = pts+ovr+plusminus;
		double ev = EVcalc(total, pos, pltype);
		double contractEV = contractval/salarycap;
		if (ev>contractEV) {
			return true;
		}else {
			return false;
		}
	}
	public static double EVcalc(int total, int pos, int pltype) {
	    int x1 = 0, x2 = 0;
	    double y1 = 0.0, y2 = 0.0;
	    double exponentBase = 0.0; 

	    
	    if (pos >= 1 && pos <= 3) {
	        switch (pltype) {
	            case 1:
	                x1 = 90; x2 = 260; y1 = 0.009; y2 = 0.19;
	                break;
	            case 2:
	                x1 = 90; x2 = 250; y1 = 0.009; y2 = 0.19;
	                break;
	            case 3:
	                x1 = 90; x2 = 250; y1 = 0.009; y2 = 0.19;
	                break;
	            case 4:
	                x1 = 90; x2 = 255; y1 = 0.009; y2 = 0.19;
	                break;
	            case 5:
	                x1 = 90; x2 = 150; y1 = 0.009; y2 = 0.10;
	                break;
	            default:
	                return 0.0; // Invalid pltype
	        }
	    }
	    
	    else if (pos >= 4 && pos <= 5) {
	        switch (pltype) {
	            case 1:
	                x1 = 90; x2 = 170; y1 = 0.009; y2 = 0.12;
	                break;
	            case 2:
	                x1 = 90; x2 = 225; y1 = 0.009; y2 = 0.19;
	                break;
	            case 3:
	                x1 = 90; x2 = 220; y1 = 0.009; y2 = 0.168;
	                break;
	            default:
	                return 0.0; // Invalid pltype for these pos values
	        }
	    }
	    else {
	        return 0.0; // Invalid pos
	    }
	    
	 
	    double ev;
	    double threshold = x2 * 0.7; 

	    if (total <= threshold) {
	        // Below the threshold, use a square root function for slower initial growth
	        double normalizedTotal = (double)(total - x1) / (threshold - x1);
	        double sqrtGrowth = y1 + (Math.sqrt(normalizedTotal) * (y2 - y1));
	        ev = Math.max(y1, Math.min(sqrtGrowth, y2)); 
	    } else {
	        // Above the threshold, use exponential growth
	        if (total > x2) {
	            total = x2;
	        }
	        // Calculate the base for the exponential function to reach y2 at x2
	        exponentBase = Math.pow((y2 / y1), 1 / (x2 - threshold));
	        // Apply the exponential function
	        ev = y1 * Math.pow(exponentBase, (total - threshold));
	    }

	    return ev;
	}
	public static double salarycapmodifier(double salarycap) {
		salarycap = salarycap + (salarycap*(Math.random()*.04+.01));
		return salarycap;
	}

	public static void sim(int age, int ovr, int pos, int pltype, int points, int goals, int assists, int plusminus, int term, double contractval, double salarycap, int pot, String name) {
		int retire = 0;
		int preovr = 0;
		int prepts = points;
		int simpts = 0;
		int sim1pts = 0;
		int pregoals = goals;
		int simgoals = 0;
		int sim1goals = 0;
		int preassists = assists;
		int simassists = 0;
		int sim1assists = 0;
		int preplusminus = plusminus;
		int simplusminus = 0;
		int sim1plusminus = 0;
		int simtotal = 0;
		int worth = 0;
		int oneloop = 0;
		int tot = 0;
		double[] contractsuccess = new double[term];
		double[] realvaluebyyear = new double[term];
		double[] goalsbyyear = new double[term];
		double[] assistsbyyear = new double[term];
		double[] plusminusbyyear = new double[term];
		int[] overallbyyear = new int[term];

			for (int j = 0; j<term; j++) {
				simgoals = 0;
				simassists = 0;
				simplusminus = 0;
				worth = 0;
				for(int i = 0; i<25; i++) {
					sim1goals = goals(ovr, pregoals, pos, pltype);
					simgoals = simgoals + sim1goals;
					sim1assists = assists(ovr, preassists, pos, pltype);
					simassists = simassists + sim1assists;
					sim1pts = sim1assists + sim1goals;
					simpts = simpts + sim1pts;
					sim1plusminus = plusminus(ovr, preplusminus, pos, pltype, sim1pts, prepts);
					simplusminus = simplusminus + sim1plusminus;
					Boolean check = EVcheck(sim1pts, ovr, sim1plusminus, pos, pltype, contractval, salarycap);
					if (check) {
						worth++;
					} else {
						continue;
					}
					
				}
				overallbyyear[j] = ovr;
				contractsuccess[j] = (worth/25.00);
				simpts = simpts/25;
				simgoals = simgoals/25;
				goalsbyyear[j] = simgoals;
				simassists = simassists/25;
				assistsbyyear[j] = simassists;
				simplusminus = simplusminus/25;
				plusminusbyyear[j] = simplusminus;
				simtotal = simplusminus+simpts+ovr;
				realvaluebyyear[j] = (EVcalc(simtotal, pos, pltype)*salarycap);
				age++;
				preovr = ovr;
				for (int i = 0; i<15; i++) {
					oneloop = overall(age, pot, preovr, simpts, prepts, simassists, preassists, simgoals, pregoals, simplusminus, preplusminus, pos, pltype);
					tot = tot + oneloop;
				}
				ovr = tot/15;
				
				tot = 0;
				oneloop = 0;
				pregoals = simgoals;
				preassists = simassists;
				prepts = simpts;
				preplusminus = simplusminus;
				salarycap = salarycapmodifier(salarycap);
			}
			
			for (int i = 1; i<term+1; i++) {
				System.out.println();
				System.out.println("Year " + i + ":");
				System.out.println("Expected goals: " + goalsbyyear[i-1]);
				System.out.println("Expected assists: " + assistsbyyear[i-1]);
				System.out.println("Expected points: " + (assistsbyyear[i-1]+goalsbyyear[i-1]));
				System.out.println("Expected overall: " + overallbyyear[i-1]);
				System.out.println("Expected +-: " + plusminusbyyear[i-1]);
				System.out.println();
				System.out.println(name + "'s contract will be worth his expected value in year " + i + " " + contractsuccess[i-1]*100 + " % of the time.");
				System.out.println();
				System.out.println(name + "'s expected value in year " + i + " will equate to a " + (double)Math.round(realvaluebyyear[i-1]*1000)/1000 + "M dollar contract");
			}
			
		

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int pltype;
		int pot = 0;
		System.out.println("What is the name of the player?");
		String name = in.nextLine();
		System.out.println("How old is " + name + "?");
		int age = in.nextInt();
		System.out.println("What is " + name + "'s overall?");
		int ovr = in.nextInt();
		
		System.out.println("What is " + name + "'s position? Enter 1 for LW, 2 for C, 3 for RW, 4 for LD, 5 for RD, 6 for GK.");
		int pos = in.nextInt();
		if (pos<4) {
			System.out.println("What is " + name + "'s player type? Enter 1 for PLY, 2 for PWF, 3 for SNP, 4 for TWF, 5 for GRN");
			pltype = in.nextInt();
			System.out.println("What is " + name + "'s potential? Enter 1 for FRN, 2 for ELI, 3 for T6F, 4 for T9F, 5 for B6F or lower");
			pot = in.nextInt();
		} else if (pos>3 && pos<6) {
			System.out.println("What is " + name + "'s player type? Enter 1 for DFD, 2 for OFD, 3 for TWD");
			pltype = in.nextInt();
			System.out.println("What is " + name + "'s potential? Enter 1 for FRN, 2 for ELI, 3 for T4D, 4 for T6D, 5 for 7D or lower");
			pot = in.nextInt();
		} else {
			System.out.println("What is " + name + "'s player type? Enter 1 for BFY, 2 for HYB, 3 for STD");
			pltype = in.nextInt();
			System.out.println("What is " + name + "'s potential? Enter 1 for FRN, 2 for ELI, 3 for STR, 4 for FSTR, 5 for BCK or lower");
			pot = in.nextInt();
		}
		System.out.println("How many points did " + name + " have last year?");
		int points = in.nextInt();
		System.out.println("How many goals did " + name + " score last year?");
		int goals = in.nextInt();
		int assists = points - goals;
		System.out.println("What was " + name + "'s +- last year?");
		int plusminus = in.nextInt();
		System.out.println("In years, how long is " + name + "'s intended contract?");
		int term = in.nextInt();
		System.out.println("In millions, how expensive is " + name + "'s contract?");
		double price = in.nextDouble();
		System.out.println("In millions, what is the salary cap in the current year?");
		double salarycap = in.nextDouble();
		
		if (pos<6) {
		sim(age,ovr, pos, pltype, points, goals, assists, plusminus, term, price, salarycap, pot, name);
		}
		


	}
	

}
