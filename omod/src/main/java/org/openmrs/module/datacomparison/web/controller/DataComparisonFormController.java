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
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.datacomparison.RowMeta;
import org.openmrs.module.datacomparison.SimpleObject;
import org.openmrs.module.metadatasharing.ImportedItem;
import org.openmrs.module.metadatasharing.ImportedPackage;
import org.openmrs.module.metadatasharing.Item;
import org.openmrs.module.metadatasharing.MetadataSharing;
import org.openmrs.module.metadatasharing.wrapper.PackageImporter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class configured as controller using annotation and mapped with the URL of 'module/basicmodule/basicmoduleLink.form'.
 */
@Controller
public class DataComparisonFormController{
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/datacomparison/comparisonView";
	
	/**
	 * Initially called after the formBackingObject method to get the landing form name  
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET, value = "module/datacomparison/datacomparisonmoduleLink.form")
	public String showForm(final ModelMap model, HttpServletRequest httpRequest, @ModelAttribute("item") ImportedItem item) throws IllegalAccessException, Exception {
		
		String packageGroupUuid = httpRequest.getParameter("packageGroupUuid");
		ImportedPackage importedPackage = MetadataSharing.getService().getImportedPackageByGroup(packageGroupUuid);
		
		
		Set<Item> items = importedPackage.getItems();
		// items is empty
		
		PackageImporter importertst = MetadataSharing.getInstance().newPackageImporter();
		// importedPackage.getSerializedPackageStream() is null
		//importertst.loadSerializedPackageStream(importedPackage.getSerializedPackageStream());
		
		
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
            List<RowMeta> rowMetaList = co.getRowMetaList(existingPatient, incomingPatient);
            
            model.addAttribute("className", existingPatient.getClass().toString());
            model.addAttribute("rowMetaList", rowMetaList);
    		
    	}
		
		return SUCCESS_FORM_VIEW;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "module/datacomparison/datacomparisonmoduleLink-simpleData.form")
	public String showFormForSimpleData(final ModelMap model) throws IllegalAccessException, Exception {
		
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
    	
    	if ((existingItem != null) && (incomingItem != null)) {
    		
    		org.openmrs.module.datacomparison.api.MetaDataComparisonService co = Context.getService(org.openmrs.module.datacomparison.api.MetaDataComparisonService.class);
            List<RowMeta> rowMetaList = co.getRowMetaList((Object) existingItem, (Object) incomingItem);
            
            model.addAttribute("className", existingItem.getClass().getName());
            model.addAttribute("rowMetaList", rowMetaList);
    		
    	}
		
		return SUCCESS_FORM_VIEW;
		
	}
	
	/**
	 * All the parameters are optional based on the necessity  
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpSession httpSession,
	                               @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		
		return SUCCESS_FORM_VIEW;
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just
	 * defined by the return type of this method
	 */
	@ModelAttribute("thePatientList")
	protected Collection<Patient> formBackingObject(HttpServletRequest request) throws Exception {
		// get all patients that have an identifier "101" (from the demo sample data)
		// see http://resources.openmrs.org/doc/index.html?org/openmrs/api/PatientService.html for
		// a list of all PatientService methods
		Collection<Patient> patients = Context.getPatientService().findPatients("101", false);
		
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
		return patients;
	}
	
}
