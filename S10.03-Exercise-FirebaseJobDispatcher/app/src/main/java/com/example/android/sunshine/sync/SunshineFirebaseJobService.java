package com.example.android.sunshine.sync;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.android.sunshine.data.WeatherContract;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SunshineFirebaseJobService extends JobService{

    private AsyncTask<Void, Void, Void> mFetchWeatherTask;

    @Override
    public boolean onStartJob(final JobParameters params) {
        mFetchWeatherTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Context context = getApplicationContext();
                SunshineSyncTask.syncWeather(context);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(params, false);
            }
        };
        mFetchWeatherTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (mFetchWeatherTask != null){
            mFetchWeatherTask.cancel(true);
        }
        return true;
    }
}