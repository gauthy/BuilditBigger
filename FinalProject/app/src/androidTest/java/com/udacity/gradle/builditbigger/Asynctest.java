package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by gowth on 2/6/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class Asynctest   {



    String Result;
    @Test
    public void testFun() {
        CountDownLatch signal = new CountDownLatch(1);
    EndpointsAsyncTask     endpointsAsyncTask = new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                Result = result;
            }
        };
        endpointsAsyncTask.execute(InstrumentationRegistry.getContext());
        try {
            signal.await(40, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(Result);
    }

}
