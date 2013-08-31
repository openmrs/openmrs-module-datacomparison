/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.datacomparison.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.datacomparison.RowMeta;
import org.openmrs.module.datacomparison.SimpleObject;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;

/**
 * This class configured as controller using annotation and mapped with the URL of 'module/basicmodule/basicmoduleLink.form'.
 */
@Controller
public class DataComparisonModuleController extends PortletController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
	    
		SimpleObject existingItem = new SimpleObject();
        SimpleObject incomingItem = new SimpleObject();
        
        existingItem.setSimpleInt(0);
        existingItem.setSimpleBoolean(null);
        existingItem.setSimpleString("Existing");
        existingItem.setSimpleFloat(12.5f);
        existingItem.setSimpleDate(Calendar.getInstance().getTime());
        
        incomingItem.setSimpleInt(0);
        incomingItem.setSimpleBoolean((Boolean) false);
        incomingItem.setSimpleString("Incoming");
        incomingItem.setSimpleFloat(13.5f);
        incomingItem.setSimpleDate(Calendar.getInstance().getTime());
        
        List<String> strList = new ArrayList<String>();
        strList.add("A");
        strList.add("B");
        strList.add("C");
        
        List<String> strListB = new ArrayList<String>();
        strListB.add("G");
        strListB.add("M");
        strListB.add("C");
        strListB.add("B");
        strListB.add("P");
        
        existingItem.setStrList(strList);
        incomingItem.setStrList(strListB);
        
        Context.addProxyPrivilege("View Patients");
    	Patient existingPatient = Context.getPatientService().getPatient(101);
    	Patient incomingPatient = Context.getPatientService().getPatient(105);
    	Context.removeProxyPrivilege("View Patients");
    	
    	if ((existingPatient != null) && (incomingPatient != null)) {
    		
    		org.openmrs.module.datacomparison.api.MetaDataComparisonService co = Context.getService(org.openmrs.module.datacomparison.api.MetaDataComparisonService.class);
            List<RowMeta> rowMetaList = new ArrayList<RowMeta>();
            try {
	            rowMetaList = co.getRowMetaList((Object) existingPatient, (Object) incomingPatient);
            }
            catch (APIException e) {
	            // TODO Auto-generated catch block
	            log.error("Error generated", e);
            }
            catch (IllegalArgumentException e) {
	            // TODO Auto-generated catch block
	            log.error("Error generated", e);
            }
            catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            log.error("Error generated", e);
            }
            catch (NoSuchFieldException e) {
	            // TODO Auto-generated catch block
	            log.error("Error generated", e);
            }
            catch (SecurityException e) {
	            // TODO Auto-generated catch block
	            log.error("Error generated", e);
            }
            
            model.put("className", existingPatient.getClass().getName());
            model.put("rowMetaList", rowMetaList);
    		
    	}
		
	}
	
}
