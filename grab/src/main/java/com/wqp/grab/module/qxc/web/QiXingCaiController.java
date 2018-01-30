package com.wqp.grab.module.qxc.web;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.wqp.grab.module.qxc.entity.*;
import com.wqp.grab.module.qxc.service.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 控制器
 *
 */
@Controller
@RequestMapping(value = "${ctx}/qxc")
public class QiXingCaiController {
    @Autowired
    private QiXingCaiService qiXingCaiService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private WinHistoryService winHistoryService;

    //大数
    private final int[] BIG_NUMBER = {5,6,7,8,9};
    //小数
    private final int[] SMALL_NUMBER = {0,1,2,3,4};
    //奇数
    private final int[] ODD_NUMBER = {1,3,5,7,9};
    //偶数
    private final int[] EVEN_NUMBER = {0,2,4,6,8};
    //质数
    private final int[] PRIME_NUMBER = {1,2,3,5,7};
    //合数
    private final int[] COMPOSITE_NUMBER = {0,4,6,8,9};
    //0路
    private final int[] ZERO_ROAD = {0,3,6,9};
    //1路
    private final int[] ONE_ROAD = {1,4,7};
    //2路
    private final int[] TWO_ROAD = {2,5,8};
    //1区
    private final int[] ONE_AREA = {0,1,2,3};
    //2区
    private final int[] TWO_AREA = {4,5,6};
    //3区
    private final int[] THREE_AREA = {7,8,9};

    //大,小标识
    private String[] TAG_B_S = {"BIG","SMALL"};
    //奇,偶标识
    private String[] TAG_O_E = {"ODD","EVEN"};
    //质,合标识
    private String[] TAG_P_C = {"PRIME","COMPOSITE"};
    //0,1,2路标识
    private String[] TAG_R_Z_O_T = {"RZERO","RONE","RTWO"};
    //1,2,3区标识
    private String[] TAG_A_O_T_T = {"AONE","ATWO","ATHREE"};






    @RequestMapping(value = "jump")
    public String jump(){
        return "module/qixingcai";
    }

    @RequestMapping(value = "statistics")
    public String statisticsJump(){
        return "module/statistics";
    }

    @RequestMapping(value = "index")
    public String index(){
        List<QiXingCai> all = qiXingCaiService.findAll();
        for (QiXingCai q : all) {
            System.out.println("Pring:"+q.getWinNumber());
        }

        Document doc = null;
        Elements elements = null;
        StringBuffer sb = new StringBuffer();
        QiXingCai qxc = null;

        for (int i = 16154;i>=16001;i--){
            try {
                doc = Jsoup.connect("http://kaijiang.500.com/shtml/qxc/"+i+".shtml?0_ala_baidu").get();
                elements = doc.select("table.kj_tablelist02");
                Elements issues = elements.select("strong");
                Elements numbers = elements.select("li.ball_orange");
                System.out.println("QiHao:"+issues.text());

                sb.delete(0,sb.length());
                for (Element e:numbers) {
                    sb.append(e.text());
                    System.out.println(e.text()+"\t");
                }
                System.out.println("\n");

                qxc = new QiXingCai();
                qxc.setIssue(Integer.parseInt(issues.text()));
                qxc.setWinNumber(sb.toString());
                qiXingCaiService.insertSelective(qxc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "module/qixingcai";
    }


    @RequestMapping(value = "statisticsByrFrequency")
    public String statisticsByrFrequency() {
        List<QiXingCai> all = qiXingCaiService.findAll();
        Statistics statistics = null;
        try {
            for (QiXingCai qxc: all) {
                int sum = qiXingCaiService.statisticsByrFrequency(new QiXingCai(qxc.getWinNumber()));
                statistics = new Statistics();
                statistics.setIssue(qxc.getIssue());
                statistics.setWinNumber(qxc.getWinNumber());
                statistics.setFrequency(sum);
                statistics.setBatch("00001");
                statisticsService.insert(statistics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "module/qixingcai";
    }

    @RequestMapping(value = "statisticsByAbove")
    public String statisticsByAbove() {
        List<QiXingCai> all = qiXingCaiService.findAll();
        Statistics statistics = null;
        for (QiXingCai qxc: all){
            statistics = new Statistics();
            statistics.setIssue(qxc.getIssue());
            statistics.setWinNumber(qxc.getWinNumber().substring(0, 4));
            statistics.setFrequency(1);
            statistics.setBatch("00002");
            statisticsService.insert(statistics);
        }

        return "module/qixingcai";
    }

    @RequestMapping(value = "statisticsBySum")
    public String statisticsBySum() {
        List<Statistics> all = statisticsService.findByExpress(new Statistics("00002"));
        Statistics statistics = null;
        int sum = 0;
        for (Statistics s: all){
            String number = s.getWinNumber();

            for(int i=0;i<number.length();i++){
                char c = number.charAt(i);
                sum += Integer.parseInt(String.valueOf(c));
            }

            statistics = new Statistics();
            statistics.setIssue(s.getIssue());
            statistics.setWinNumber(String.valueOf(sum));
            statistics.setFrequency(1);
            statistics.setBatch("00004");
            statisticsService.insert(statistics);
            sum = 0;
        }

        return "module/qixingcai";
    }

    @RequestMapping(value = "qixingcai")
    public String statistics() {
        List<QiXingCai> all = qiXingCaiService.findAll();
        Statistics statistics = null;

        int sum = 0;
        int ge = 0;
        int shi = 0;
        int bai = 0;
        int qian = 0;
        for (QiXingCai qxc: all){
            String number = qxc.getWinNumber();
            for(int i=0;i<4;i++){//取前4位
                String  c = String.valueOf(number.charAt(i));
                int temp = Integer.parseInt(c);
                if(i == 0){
                    ge = temp;
                }else if (i == 1){
                    shi = temp;
                }else if (i == 2){
                    bai = temp;
                }else if (i == 3){
                    qian = temp;
                }
                sum += temp;
            }

            statistics = new Statistics();
            statistics.setIssue(qxc.getIssue());
            statistics.setWinNumber(qxc.getWinNumber());
            statistics.setFrequency(1);
            statistics.setBatch("00001");
            statistics.setAboveFour(qxc.getWinNumber().substring(0, 4));
            statistics.setAboveFourSum(String.valueOf(sum));
            statistics.setGeWei(String.valueOf(ge));
            statistics.setShiWei(String.valueOf(shi));
            statistics.setBaiWei(String.valueOf(bai));
            statistics.setQianWei(String.valueOf(qian));
            statisticsService.insert(statistics);
            sum = 0;
            ge = 0;
            shi = 0;
            bai = 0;
            qian = 0;
        }

        return "module/qixingcai";
    }

    @ResponseBody
    @RequestMapping(value = "operateByDynamicTable",method = RequestMethod.POST)
    public String operateByDynamicTable(String issue,String winNumber,String classify,String origin,String zero,String one,String two,String three,String four,String five,String six,String seven,String eight,String nine,String frequencyValidity,String radix){
        JSONObject jsonObject = new JSONObject();

        if(StringUtils.isEmpty(issue) || StringUtils.isEmpty(classify) || StringUtils.isEmpty(origin)){
            jsonObject.put("state",false);
        }else {
            Dynamic dynamic = new Dynamic();
            if(!StringUtils.isEmpty(issue))dynamic.setIssue(Integer.parseInt(issue));
            if(!StringUtils.isEmpty(winNumber)) dynamic.setWinNumber(winNumber);
            if(!StringUtils.isEmpty(classify)) dynamic.setClassify(classify);
            if(!StringUtils.isEmpty(origin)) dynamic.setOrigin(origin);
            if(!StringUtils.isEmpty(zero)) dynamic.setZero(zero);
            if(!StringUtils.isEmpty(one)) dynamic.setOne(one);
            if(!StringUtils.isEmpty(two)) dynamic.setTwo(two);
            if(!StringUtils.isEmpty(three)) dynamic.setThree(three);
            if(!StringUtils.isEmpty(four)) dynamic.setFour(four);
            if(!StringUtils.isEmpty(five)) dynamic.setFive(five);
            if(!StringUtils.isEmpty(six)) dynamic.setSix(six);
            if(!StringUtils.isEmpty(seven)) dynamic.setSeven(seven);
            if(!StringUtils.isEmpty(eight)) dynamic.setEight(eight);
            if(!StringUtils.isEmpty(nine)) dynamic.setNine(nine);
            if(!StringUtils.isEmpty(frequencyValidity)) dynamic.setFrequencyValidity(Integer.parseInt(frequencyValidity));
            if(!StringUtils.isEmpty(radix)) dynamic.setRadix(Integer.parseInt(radix));
            dynamicService.insertSelective(dynamic);

            jsonObject.put("state",true);
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "operateByRecommendTable")
    public String operateByRecommendTable(){

        return "module/qixingcai";
    }


    @RequestMapping(value = "calculator")
    public String calculator(){
        int[] ge = {0,1,2,4,5,6,7,9};
        int[] shi = {2,3,5,7,8,9};
        int[] bai = {0,1,2,3,4,5,6,8};
        int[] qian = {1,2,4,5,7,8,9};

        StringBuffer sb = new StringBuffer();
        for (int i=0;i<ge.length;i++){
            for (int j=0;j<shi.length;j++){
                for (int m=0;m<bai.length;m++){
                    for (int n=0;n<qian.length;n++){
                        sb.delete(0,sb.length());

                        sb.append(ge[i]);
                        sb.append(shi[j]);
                        sb.append(bai[m]);
                        sb.append(qian[n]);

                        int num = qiXingCaiService.statisticsByrFrequency(new QiXingCai(sb.toString()));
                        if(num == 0){
                            recommendService.insertSelective(new Recommend(17126,sb.toString()));
                        }
                    }
                }
            }
        }

        return "module/qixingcai";
    }

    @ResponseBody
    @RequestMapping(value = "statisticsHistory")
    public String statisticsHistory(String code,String value){
        List<Statistics> results = new ArrayList<Statistics>();
        JSONObject object = new JSONObject();

        Statistics statistics = new Statistics();
        switch (code){
            case "1"://个位
                statistics.setGeWei(value);
                break;
            case "2"://十位
                statistics.setShiWei(value);
                break;
            case "3"://百位
                statistics.setBaiWei(value);
                break;
            case "4"://千位
                statistics.setQianWei(value);
                break;
            default:
                break;
        }

        List<Statistics> byExpress = statisticsService.findByExpress(statistics);
        for (Statistics s:byExpress) {
            Statistics ss = new Statistics();
            ss.setIssue(s.getIssue()+1);
            List<Statistics> byExp = statisticsService.findByExpress(ss);
            if(byExp != null){
                results.addAll(byExp);
            }
        }

        int zero=0,one=0,two=0,three=0,four=0,five=0,six=0,seven=0,eight=0,nine=0;
        for (Statistics s:results) {
            String v = "";
            switch (code){
                case "1"://个位
                    v = s.getGeWei();
                    break;
                case "2"://十位
                    v = s.getShiWei();
                    break;
                case "3"://百位
                    v = s.getBaiWei();
                    break;
                case "4"://千位
                    v = s.getQianWei();
                    break;
                default:
                    break;
            }

            switch (v) {
                case "0":
                    zero++;
                    break;
                case "1":
                    one++;
                    break;
                case "2":
                    two++;
                    break;
                case "3":
                    three++;
                    break;
                case "4":
                    four++;
                    break;
                case "5":
                    five++;
                    break;
                case "6":
                    six++;
                    break;
                case "7":
                    seven++;
                    break;
                case "8":
                    eight++;
                    break;
                case "9":
                    nine++;
                    break;
                default:
                    break;
            }
        }

        object.put("statistics",results);
        object.put("zero",zero);
        object.put("one",one);
        object.put("two",two);
        object.put("three",three);
        object.put("four",four);
        object.put("five",five);
        object.put("six",six);
        object.put("seven",seven);
        object.put("eight",eight);
        object.put("nine",nine);
        object.put("pre_value",value);
        object.put("code",code);

        return object.toJSONString();
    }

    /**
     * 预测未来推出的数字组合
     * @return
     */
    @RequestMapping(value = "analystFeature")
    public String analystFeature(Model model){
        List<List> resultTag = new ArrayList<List>();
        List<List> resultNum = new ArrayList<List>();
        List<String> res = null;
        //顺序:大小->奇偶->质合->012路->123区
        for (int i=0;i<TAG_B_S.length;i++){
            for (int j=0;j<TAG_O_E.length;j++){
                for (int m=0;m<TAG_P_C.length;m++){
                    for (int n=0;n<TAG_R_Z_O_T.length;n++){
                        for (int t=0;t<TAG_A_O_T_T.length;t++){
                            res = new ArrayList<String>();
                            res.add(TAG_B_S[i]);
                            res.add(TAG_O_E[j]);
                            res.add(TAG_P_C[m]);
                            res.add(TAG_R_Z_O_T[n]);
                            res.add(TAG_A_O_T_T[t]);
                            resultTag.add(res);
                        }
                    }
                }
            }
        }

        for (List l:resultTag) {
            resultNum.add(combination(l));
            for (Object o:l) {
                System.out.print(o);
                System.out.print("\t");
            }
            System.out.print("\n");
        }

        model.addAttribute("resultTag",resultTag);
        model.addAttribute("resultNum",resultNum);
        return "module/analystfeature";
    }


    /**
     * 根据标识进行组合
     * @param tagList
     * @return
     */
    private List<String> combination(List tagList){
        List<int[]> processData = new ArrayList<int[]>();
        StringBuffer sb = null;
        List<String> result = new ArrayList<String>();
        for(int i=0;i<tagList.size();i++){
            String tag = (String) tagList.get(i);
            switch (tag){
                case "BIG":
                    processData.add(BIG_NUMBER);
                    break;
                case "SMALL":
                    processData.add(SMALL_NUMBER);
                    break;
                case "ODD":
                    processData.add(ODD_NUMBER);
                    break;
                case "EVEN":
                    processData.add(EVEN_NUMBER);
                    break;
                case "PRIME":
                    processData.add(PRIME_NUMBER);
                    break;
                case "COMPOSITE":
                    processData.add(COMPOSITE_NUMBER);
                    break;
                case "RZERO":
                    processData.add(ZERO_ROAD);
                    break;
                case "RONE":
                    processData.add(ONE_ROAD);
                    break;
                case "RTWO":
                    processData.add(TWO_ROAD);
                    break;
                case "AONE":
                    processData.add(ONE_AREA);
                    break;
                case "ATWO":
                    processData.add(TWO_AREA);
                    break;
                case "ATHREE":
                    processData.add(THREE_AREA);
                    break;
                default:
                    break;
            }
        }

        //顺序：
        for (int i=0;i<processData.get(0).length;i++){
            for (int j=0;j<processData.get(1).length;j++){
                for (int m=0;m<processData.get(2).length;m++){
                    for (int n=0;n<processData.get(3).length;n++){
                        for (int t=0;t<processData.get(4).length;t++){
                            sb = new StringBuffer();
                            if(isExists(BIG_NUMBER,processData.get(0)[i])){//大数
                                sb.append("大");
                            }else if(isExists(SMALL_NUMBER,processData.get(0)[i])){//小数
                                sb.append("小");
                            }

                            if(isExists(ODD_NUMBER,processData.get(1)[j])){//奇数
                                sb.append("奇");
                            }else if(isExists(EVEN_NUMBER,processData.get(1)[j])){//偶数
                                sb.append("偶");
                            }

                            if(isExists(PRIME_NUMBER,processData.get(2)[m])){//质数
                                sb.append("质");
                            }else if(isExists(COMPOSITE_NUMBER,processData.get(2)[m])){//合数
                                sb.append("合");
                            }

                            if(isExists(ZERO_ROAD,processData.get(3)[n])){//0路
                                sb.append("0");
                            }else if(isExists(ONE_ROAD,processData.get(3)[n])){//1路
                                sb.append("1");
                            }else if(isExists(TWO_ROAD,processData.get(3)[n])) {//2路
                                sb.append("2");
                            }

                            if(isExists(ONE_AREA,processData.get(4)[t])){//1区
                                sb.append("1");
                            }else if(isExists(TWO_AREA,processData.get(4)[t])){//2区
                                sb.append("2");
                            }else if(isExists(THREE_AREA,processData.get(4)[t])) {//3区
                                sb.append("3");
                            }

                            sb.append("-->");

                            if((processData.get(0)[i] == processData.get(1)[j]) && (processData.get(0)[i] == processData.get(2)[m]) && (processData.get(0)[i] == processData.get(3)[n]) && (processData.get(0)[i] == processData.get(4)[t])){
                                sb.append(processData.get(0)[i]);
                                sb.append(processData.get(1)[j]);
                                sb.append(processData.get(2)[m]);
                                sb.append(processData.get(3)[n]);
                                sb.append(processData.get(4)[t]);
                            }else {
                                sb.append("无效");
                            }

                            if(!result.contains(sb.toString())) result.add(sb.toString());
                        }
                    }
                }
            }
        }



//        for (int i=0;i<processData.get(0).length;i++){
//            for (int j=0;j<processData.get(1).length;j++){
//                for (int m=0;m<processData.get(2).length;m++){
//                    for (int n=0;n<processData.get(3).length;n++){
//                        for (int t=0;t<processData.get(4).length;t++){
//                            sb = new StringBuffer();
//                            sb.append(processData.get(0)[i]);
//                            sb.append(processData.get(1)[j]);
//                            sb.append(processData.get(2)[m]);
//                            sb.append(processData.get(3)[n]);
//                            sb.append(processData.get(4)[t]);
//                            result.add(sb.toString());
//                        }
//                    }
//                }
//            }
//        }

        return result;
    }

    @RequestMapping(value = "winHistory")
    public void winHistory(){
        Document doc = null;
        Element element = null;
        String result = "";
        WinHistory record;

        try {
//            doc = Jsoup.connect("http://epsg.io/3333").get();
//            element = doc.getElementById("s_wkt_text");
//            result = element.text();
            File file = new File("D:\\temp\\shuju2.html");
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            String cache;
            while((cache = br.readLine()) != null){
                sb.append(cache);
            }
            doc = Jsoup.parseBodyFragment(sb.toString());
//            doc = Jsoup.connect("D:\\temp\\shuju2.html").get();
            Elements allElements = doc.getElementsByTag("tr");
            for (Element e:allElements) {
                Element child0 = e.child(0);
                Element child1 = e.child(1);
                record = new WinHistory();
                char[] chars = child1.text().toCharArray();
                record.setIssue(child0.text());
                record.setWinNumber(child1.text());
                record.setFrontNumber(child1.text().substring(0,4));
                record.setQian(String.valueOf(chars[0]));
                record.setBai(String.valueOf(chars[1]));
                record.setShi(String.valueOf(chars[2]));
                record.setGe(String.valueOf(chars[3]));
                winHistoryService.insertSelective(record);
                System.out.println(child0.text()+","+child1.text());
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "validityPage")
    public String validityPage(){
        return "module/validity";
    }

    @ResponseBody
    @RequestMapping(value = "validity")
    public List<String> validity(String qian,String bai,String shi,String ge){
        List<String> result = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        WinHistory winHistory = null;
        char[] charsQ = qian.toCharArray();
        char[] charsB = bai.toCharArray();
        char[] charsS = shi.toCharArray();
        char[] charsG = ge.toCharArray();
        for(int i=0;i<charsQ.length;i++){
            for(int j=0;j<charsB.length;j++){
                for(int m=0;m<charsS.length;m++){
                    for(int n=0;n<charsG.length;n++){
                        sb.delete(0,sb.length());
                        sb.append(charsQ[i]);
                        sb.append(charsB[j]);
                        sb.append(charsS[m]);
                        sb.append(charsG[n]);
                        winHistory = new WinHistory();
                        winHistory.setFrontNumber(sb.toString());
                        List<WinHistory> byExpress = winHistoryService.findByExpress(winHistory);
                        if(byExpress != null && byExpress.size() > 0){
                            //历史上出现过则忽略
                        }else {
                            result.add(sb.toString());
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isExists(int[] arrays,int cond){
        boolean flag = false;
        for(int i=0;i<arrays.length;i++){
            if(arrays[i] == cond){
                flag = true;
                break;
            }
        }
        return flag;
    }


    public static void main(String [] args) throws IOException {
        Document doc = null;
        Element element = null;
        String result = "";

        try {
//            doc = Jsoup.connect("http://epsg.io/3333").get();
//            element = doc.getElementById("s_wkt_text");
//            result = element.text();
              doc = Jsoup.connect("D:\\temp\\shuju2.html").get();

           System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
