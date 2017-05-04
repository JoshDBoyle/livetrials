package com.adobe.livetrials.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(
		adaptables=Resource.class, 
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
		resourceType="weretail/components/content/title")
@Exporter(
		name="jackson", 
		extensions="json")
public class MyComponentModel {
	@Self
	private Resource resource;
	
	@Inject @Named("jcr:title") @Default(values = "No title provided")
	private String pageTitle;
	

	@Inject @Named("jcr:description") @Default(values = "No description provided")
	private String description;
	
	@Inject @Named("jcr:created")
	private String created;
	
	private int numChildren = 0;
	
	@PostConstruct
    private void init() {
		resource.getChildren().forEach(i -> numChildren++);
    }
	
	public String getPageTitle() {
		return pageTitle;
	}

	public String getDescription() {
		return description;
	}

	public String getCreated() {
		return created;
	}

	public int getNumChildren() {
		return numChildren;
	}
}