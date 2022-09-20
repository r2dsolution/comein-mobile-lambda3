package com.r2dsolution.comein.util;

import com.amazonaws.regions.Regions;

public class RegionsUtils {
	public static Regions initRegions(String region) {
    	if (region!=null && region.trim().equals("ap-southeast-1")) {
    		return Regions.AP_SOUTHEAST_1;
    	} else {
    		return Regions.AP_SOUTHEAST_1;
    	}
    }
}
