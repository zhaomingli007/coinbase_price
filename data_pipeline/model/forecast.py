import pandas as pd
import numpy as np
from statsmodels.iolib.smpickle import load_pickle
import sys
import datetime

def forecast(dataDir):
    #Load the model
    results_ARIMA = load_pickle(dataDir + 'model/results_ARIMA.pickle')

    #Forecast next 15 days's price
    dt = datetime.datetime.now()
    base = datetime.date(dt.year, dt.month, dt.day)
    numdays = 15
    dates = [pd.Timestamp(base + datetime.timedelta(days=x)) for x in range(1, numdays+1)]
    forecast = pd.Series(results_ARIMA.forecast(steps=15)[0],dates)
    forecast = np.exp(forecast)
    print(forecast)
    forecast.to_csv(dataDir + 'forecast/forecast.csv',index_label='time',header=['price'])

if __name__ == "__main__":
    dataDir = str(sys.argv[1])
    print('dataDir:'+dataDir)
    forecast(dataDir)