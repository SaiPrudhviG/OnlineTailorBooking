package com.example.prudhvi.onlinetailorbooking;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Main_FramelayoutTest {

    @Rule
    public ActivityTestRule<Main_Framelayout> obj=new ActivityTestRule<Main_Framelayout>(Main_Framelayout.class);
    private Main_Framelayout obj1=null;
    @Before
    public void setUp() throws Exception
    {
        obj1=obj.getActivity();
    }

    @Test
    public  void testLaunch()
    {
        View v=obj1.findViewById(R.id.mainframe_frame);
        assertNotNull(v);
    }
    @After
    public void tearDown() throws Exception
    {
        obj1=null;
    }
}