package com.jy.reimbursement.web;

import com.jy.reimbursement.ReimbursementModuleApplication;
import com.jy.reimbursement.common.util.Workflow;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ReimbursementModuleApplication.class)
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class CrateActivite {
    @Test
    public void test(){
        BpmnModel bpmnModel = Workflow.createBpmnModel();


        Process process = Workflow.createProcess("process_id","process_name");

        bpmnModel.addProcess(process);

        StartEvent startEvent = Workflow.crateStartEvent("start_id","start_name");
        EndEvent endEvent = Workflow.crateEndEvent("end_id","end_name");

        SequenceFlow sequenceFlow = Workflow.createSequenceFlow("squenceFlowId","squenceFlowName",startEvent.getId(),endEvent.getId(),"");
        List<SequenceFlow> list = new ArrayList<>();
        list.add(sequenceFlow);
        startEvent.setOutgoingFlows(list);
        endEvent.setIncomingFlows(list);
        process.addFlowElement(startEvent);
        process.addFlowElement(endEvent);
        process.addFlowElement(sequenceFlow);
        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
//验证失败信息的封装ValidationError
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
        System.out.println(validate.size());
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        for(FlowElement e : flowElements) {
            if (e instanceof StartEvent){
                StartEvent startEvent1 = (StartEvent) e;
                System.out.println(startEvent1.getOutgoingFlows().size());
            }
            System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
        }
    }







}
