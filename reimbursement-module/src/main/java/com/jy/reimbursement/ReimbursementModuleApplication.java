package com.jy.reimbursement;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niujiwei
 */
@SpringBootApplication
public class ReimbursementModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReimbursementModuleApplication.class, args);
	}



}
