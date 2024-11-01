import pandas as pd
import numpy as np
import statsmodels.api as sm

df = pd.read_excel('/Users/zachsusee/desktop/compsci/nhl23/data.xlsx', skiprows=1)

df.columns = ['Player', 'Age', 'Pos', 'GP', 'G', 'A', 'PTS', 'PM', 'CAPHIT', 'TOI']

df = df.dropna(subset=['G', 'A', 'PTS', 'PM', 'Age', 'TOI', 'CAPHIT'])

forward_positions = ['LW', 'C', 'RW']
defense_positions = ['LD', 'RD', 'D']  

forwards = df[df['Pos'].isin(forward_positions)]
defensemen = df[df['Pos'].isin(defense_positions)]

salary_cap = 82500000  
max_player_cap_hit = 0.2 * salary_cap  

X_fwd = forwards[['G', 'A', 'PM', 'TOI']].copy()
y_fwd = forwards['CAPHIT']
y_fwd_log = np.log(y_fwd)

G_mean_fwd = X_fwd['G'].mean()
A_mean_fwd = X_fwd['A'].mean()
TOI_mean_fwd = X_fwd['TOI'].mean()

X_fwd['G_centered'] = X_fwd['G'] - G_mean_fwd
X_fwd['A_centered'] = X_fwd['A'] - A_mean_fwd
X_fwd['TOI_centered'] = X_fwd['TOI'] - TOI_mean_fwd

X_fwd_model = X_fwd[['G_centered', 'A_centered', 'PM', 'TOI_centered']]
X_fwd_model = sm.add_constant(X_fwd_model)

model_fwd = sm.OLS(y_fwd_log, X_fwd_model).fit()
print("Forwards Regression Results:")
print(model_fwd.summary())

G_kuch = 44
A_kuch = 100
PM_kuch = 8
TOI_kuch = 21.66666667  


G_centered_kuch = G_kuch - G_mean_fwd
A_centered_kuch = A_kuch - A_mean_fwd
TOI_centered_kuch = TOI_kuch - TOI_mean_fwd


kucherov_data = pd.DataFrame({
    'const': [1],
    'G_centered': [G_centered_kuch],
    'A_centered': [A_centered_kuch],
    'PM': [PM_kuch],
    'TOI_centered': [TOI_centered_kuch]
})


log_caphit_pred_kuch = model_fwd.predict(kucherov_data)[0]
caphit_pred_kuch = np.exp(log_caphit_pred_kuch)
caphit_pred_kuch_capped = min(caphit_pred_kuch, max_player_cap_hit)

print(f"\nPredicted CAPHIT for Nikita Kucherov: ${caphit_pred_kuch:,.2f}")
print(f"Capped CAPHIT (max 20% of salary cap): ${caphit_pred_kuch_capped:,.2f}")


X_def = defensemen[['G', 'A', 'PM', 'TOI']].copy()
y_def = defensemen['CAPHIT']
y_def_log = np.log(y_def)


G_mean_def = X_def['G'].mean()
A_mean_def = X_def['A'].mean()
TOI_mean_def = X_def['TOI'].mean()

X_def['G_centered'] = X_def['G'] - G_mean_def
X_def['A_centered'] = X_def['A'] - A_mean_def
X_def['TOI_centered'] = X_def['TOI'] - TOI_mean_def


X_def_model = X_def[['G_centered', 'A_centered', 'PM', 'TOI_centered']]
X_def_model = sm.add_constant(X_def_model)


model_def = sm.OLS(y_def_log, X_def_model).fit()
print("\nDefensemen Regression Results:")
print(model_def.summary())


G_makar = 28
A_makar = 58
PM_makar = 48
TOI_makar = 24.76 

G_centered_makar = G_makar - G_mean_def
A_centered_makar = A_makar - A_mean_def
TOI_centered_makar = TOI_makar - TOI_mean_def


makar_data = pd.DataFrame({
    'const': [1],
    'G_centered': [G_centered_makar],
    'A_centered': [A_centered_makar],
    'PM': [PM_makar],
    'TOI_centered': [TOI_centered_makar]
})


log_caphit_pred_makar = model_def.predict(makar_data)[0]
caphit_pred_makar = np.exp(log_caphit_pred_makar)
caphit_pred_makar_capped = min(caphit_pred_makar, max_player_cap_hit)

print(f"\nPredicted CAPHIT for Cale Makar: ${caphit_pred_makar:,.2f}")
print(f"Capped CAPHIT (max 20% of salary cap): ${caphit_pred_makar_capped:,.2f}")




