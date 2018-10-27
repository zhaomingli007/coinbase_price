import pandas as pd
from statsmodels.tsa.stattools import acf, pacf
import numpy as np
from statsmodels.tsa.arima_model import ARIMA
import sys
import glob
import os

def train(dataDir):
    #Load data
    paritions = [dataDir+'/datalake/daily_price/'+f for f in os.listdir(dataDir+'/datalake/daily_price/') if f.startswith('receive_dt=')]
    csvFiles = list(map(lambda folder : [folder+"/"+file for file in os.listdir(folder) if file.endswith('csv')][0], paritions))
    dfs = [pd.read_csv(file,index_col= 'time') for file in csvFiles]
    df = pd.concat(dfs)

    df.index = pd.to_datetime(df.index)
    df = df.sort_index()
    # Generat time series
    ts = df['price']
    #Log transformation 
    ts_log = np.log(ts)

    #Eliminate trend 
    ts_log_diff = ts_log - ts_log.shift()
    ts_log_diff.dropna(inplace=True)

    #Generate model
    model = ARIMA(ts_log, order=(2, 1, 2))  
    results_ARIMA = model.fit(disp=-1)  
    results_ARIMA.save(dataDir + 'model/results_ARIMA.pickle')

if __name__ == "__main__":
    dataDir = str(sys.argv[1])
    print('dataDir:'+dataDir)
    train(dataDir)