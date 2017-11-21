package com.jy.reimbursement.web;

import com.jy.reimbursement.common.util.ImaGegenerateUtils;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author niujiwei
 * @date 2017/11/14
 */
@RestController
public class MenuController {
    @Autowired
    ProcessEngine processEngine;

    @GetMapping("/menu")
    public void activit() {
        //实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
//开始节点的属性
            StartEvent startEvent = new StartEvent();
            startEvent.setId("start1shareniu");
            startEvent.setName("start1shareniu");
//普通的UserTask节点
            UserTask userTask = new UserTask();
            userTask.setId("userTask1shareniu");
            userTask.setName("userTask1shareniu");


//结束节点属性
            EndEvent endEvent = new EndEvent();
            endEvent.setId("endEventshareniu");
            endEvent.setName("endEventshareniu");
//连线信息
            List<SequenceFlow> sequenceFlows = new ArrayList<SequenceFlow>();
            List<SequenceFlow> toEnd = new ArrayList<SequenceFlow>();
            SequenceFlow s1 = new SequenceFlow();
            s1.setId("starttouserTask");
            s1.setName("starttouserTask");
            s1.setSourceRef("start1shareniu");
            s1.setTargetRef("userTask1shareniu");

            sequenceFlows.add(s1);
            SequenceFlow s2 = new SequenceFlow();
            s2.setId("userTasktoend");
            s2.setName("userTasktoend");
            s2.setSourceRef("userTask1shareniu");
            s2.setTargetRef("endEventshareniu");
            toEnd.add(s2);
            startEvent.setOutgoingFlows(sequenceFlows);
            userTask.setOutgoingFlows(toEnd);
            userTask.setIncomingFlows(sequenceFlows);
            endEvent.setIncomingFlows(toEnd);
//Process对象
            Process process = new Process();
            process.setId("process1");
            process.addFlowElement(startEvent);
            process.addFlowElement(s1);
            process.addFlowElement(userTask);
            process.addFlowElement(s2);
            process.addFlowElement(endEvent);
            bpmnModel.addProcess(process);


    /*    processEngine.getProcessEngineConfiguration().setProcessDiagramGenerator(new DefaultProcessDiagramGenerator());
       InputStream inputStream = new DefaultProcessDiagramGenerator()
                .generateDiagram(bpmnModel, "png",
                        processEngine.getProcessEngineConfiguration().getActivityFontName(),
                        processEngine.getProcessEngineConfiguration().getLabelFontName(),
                        processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                        processEngine.getProcessEngineConfiguration().getClassLoader(),1.0);
        try {
            OutputStream outputStream = new FileOutputStream(new File("d:1.png"));
            byte[] b = new byte[1024];
            int len;
            while ((len=inputStream.read(b))!=-1){
                outputStream.write(b, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        processEngine.getRepositoryService().createDeployment().addBpmnModel("1hello.bpmn", bpmnModel).deploy();
*/

/*
        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
        System.out.println(validate.size());
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("process1");
                   System.out.println(processInstance.getBusinessKey())
                */
        ;
    }

    @GetMapping("/menu/{taskID}")
    public String compleate(@PathVariable("taskID") String taskId) {
     /*   List<String> activeActivityIds = new ArrayList<>(0);
        activeActivityIds.add("usertask2");
        List<String> highLightedFlows = new ArrayList<>(0);
        List<String> userTasks = new ArrayList<>();
        userTasks.add("usertask1");
        userTasks.add("usertask2");
        userTasks.add("usertask3");
        InputStream in = ImaGegenerateUtils.gegenerate(userTasks, activeActivityIds, highLightedFlows);
        FileOutputStream fw;
        try {
            fw = new FileOutputStream("D:/zxn.jpg");
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) { // 将byte数据读到最多buf长度的buf数组中
                fw.write(buf, 0, len); // 将buf中 从0-len长度的数据写到文件中
            }
        } catch (IOException e) {
            System.out.println("输入错误");
        }*/
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(taskId);
        return "n";
    }

    @GetMapping("/menu/create")
    public void create(){
        //实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        process.setId("t1111");
        process.setName("zzz");
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start111");
        startEvent.setName("name");
        GraphicInfo graphicInfo = gegenerateGraphicInfo(ImaGegenerateUtils.startEvent_X,
                ImaGegenerateUtils.startEvent_Y, ImaGegenerateUtils.startEvent_WIDTH, ImaGegenerateUtils.startEvent_HEIGHT);
        bpmnModel.addGraphicInfo(startEvent.getId(), graphicInfo);

        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        endEvent.setName("endName");

        SequenceFlow s2 = new SequenceFlow();
        s2.setId("SequenceFlow");
        s2.setName("SequenceFlow");
        s2.setSourceRef(startEvent.getId());
        s2.setTargetRef(endEvent.getId());
        List<SequenceFlow> toEnd = new ArrayList<SequenceFlow>();
        toEnd.add(s2);
        GraphicInfo graphicInfo2 = gegenerateGraphicInfo(ImaGegenerateUtils.startEvent_X,
                ImaGegenerateUtils.startEvent_Y+10, ImaGegenerateUtils.startEvent_WIDTH, ImaGegenerateUtils.startEvent_HEIGHT);
        bpmnModel.addGraphicInfo(endEvent.getId(), graphicInfo2);
        startEvent.setOutgoingFlows(toEnd);
        endEvent.setIncomingFlows(toEnd);

        process.addFlowElement(startEvent);
        process.addFlowElement(endEvent);
        process.addFlowElement(s2);
        bpmnModel.addProcess(process);
        //验证bpmnModel 是否是正确的bpmn xml文件
        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
//验证失败信息的封装ValidationError
        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
        System.out.println(validate.size());

        InputStream inputStream = new DefaultProcessDiagramGenerator()
                .generateDiagram(bpmnModel, "png",
                        processEngine.getProcessEngineConfiguration().getActivityFontName(),
                        processEngine.getProcessEngineConfiguration().getLabelFontName(),
                        processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                        processEngine.getProcessEngineConfiguration().getClassLoader(),1.0);
        try {
            OutputStream outputStream = new FileOutputStream(new File("d:1111.png"));
            byte[] b = new byte[1024];
            int len;
            while ((len=inputStream.read(b))!=-1){
                outputStream.write(b, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  processEngine.getRepositoryService().createDeployment().addBpmnModel("hello.bpmn", bpmnModel).deploy();



    }
    public static void Createto(BpmnModel bpmnModel){
        //创建转换对象
        BpmnXMLConverter comverter = new BpmnXMLConverter();
        //把bpmnModel对象转换成字符
        byte[] bytes = comverter.convertToXML(bpmnModel);
        String xmlContenxt = bytes.toString();
        System.out.println(new String(bytes));
    }
    private static GraphicInfo gegenerateGraphicInfo(double x, double y,
                                                     double width, double height) {
        GraphicInfo graphicInfo1 = new GraphicInfo();
        graphicInfo1.setWidth(width);
        graphicInfo1.setHeight(height);
        graphicInfo1.setX(x);
        graphicInfo1.setY(y);
        return graphicInfo1;
    }


    @GetMapping("/menu/nihao/{id}")
    public void getNext(@PathVariable("id") String id){

        processEngine.getRepositoryService().createDeploymentQuery();
        BpmnModel model = processEngine.getRepositoryService().getBpmnModel(id);
        if(model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for(FlowElement e : flowElements) {
                System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
            }
        }
    }


}
