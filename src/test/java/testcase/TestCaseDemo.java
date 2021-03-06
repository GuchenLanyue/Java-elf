package testcase;

import com.javaelf.utils.JsonUtils;
import com.javaelf.utils.LoadStaticConfigUtil;
import com.javaelf.utils.RestTemplateUtils;
import com.javaelf.utils.VariableUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestCaseDemo {

    @Test
    public void case1(){
        String str1 = "qzcsbj";
        String str2 = "qzcsbj";
        String str3 = new String("qzcsbj");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
    }

    @Test
    public void case3(){
        String url = "http://192.168.1.57:811/platform-product/commodity/page-others";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzIxNDAwMTQ5NjAzMzg5NDQwIiwiYXVkIjoiMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYiLCJjb21wYW55SWRzIjpbMTI2MDQ2MzA2NzEwODYxMDA0OF0sIm5iZiI6MTYwNDAyMTM3MSwiaXNzIjoicmVzdGFwaXVzZXIiLCJleHAiOjE2MDQwMjg1NzEsInVzZXJJZCI6MTMyMTQwMDE0OTYwMzM4OTQ0MCwiaWF0IjoxNjA0MDIxMzcxLCJqdGkiOiJiMzBmZjJjMi1kZTUxLTQ4ODYtYjNkMy01ODQ2YWIxOTc4NjkifQ.d73DENmtduK7M53HqVhIYb4Aq_1nOWAtvfBzOk3Wmzk";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        ResponseEntity responseEntity = RestTemplateUtils.get(url,headers,Object.class,"");
        Map<String,Object> map = (Map<String, Object>) responseEntity.getBody();
        System.out.println(JsonUtils.mapToJson(map));
    }

    @Test
    public String case4(){
        String str = "\ncom.javaelf.service.TestDataFactory\n";
        String[] out = str.split("\n");
        for (int i=0;i<out.length;i++){
            return str;
        }
        return null;
    }
    @Test
    public void case5(){
        System.out.println(case4());
    }

    @Test
    public void case6(){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        String url1 = " http://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww27d6f876d80ceec6&corpsecret=elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw8";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Content-Type","application/json");
        Map<String,Object> map = new HashMap<>();
        map.put("corpid","ww27d6f876d80ceec6");
        map.put("corpsecret","elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw8");
        String json = JsonUtils.obj2json(map);
//        String responseEntity = RestTemplateUtils.get(url,String.class,json).getBody();
        String responseEntity = RestTemplateUtils.get(url,String.class,json).getBody();
        System.out.println(responseEntity);
    }
    @Test
    public void  case7(){
        //字符串转map类型Map<String, Object>，再转 Map<String, String>，放入 httpHeaders.setAll(mapStr);
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        String header = "{\"corpid\":\"${corpidTrue}\",\"corpsecret\":\"${corpidFalse}\"}";
        Map<String, Object> map1 = JsonUtils.json2map(header);
        Map<String, String> mapStr = new HashMap<>();
        map1.keySet().stream().peek(mapKey -> {
            mapStr.put(mapKey, map1.get(mapKey).toString());
        }).collect(Collectors.toList());
        System.out.println(mapStr);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(mapStr);
        String responseEntity = RestTemplateUtils.get(url,httpHeaders,String.class).getBody();
        System.out.println(responseEntity);
    }

    /**
     * 二分查找,方法
     * */
    @Test
    public int case8(int[] num,int startNum,int endNum,int findNum){
        if (startNum <= endNum){
            //算出中间位置
            int middle = (startNum+endNum)/2;
            //数组中间数字
            int middleValue = num[middle];
            //二分查找
            if (findNum == middleValue){
                return middle;
            }else if (findNum < middle){
                //小于中间值，在中间值之前的数据中查找，使用递归
                return case8(num,startNum,middle-1,findNum);
            }else {
                //大于中间值在，在中间值之前查找
                return case8(num,middle+1,endNum,findNum);
            }
        }
        return -1;
    }

    @Test
    public void case9(){
        //要查到的数组
        int[] binaryNums = new int[100];
        //一次循环加入数据
        for (int i=0;i<100;i++){
            binaryNums[i] =(i+1);
        }
        //定义要查找到的值
        int findNum = 65;
        int findResult = case8(binaryNums,0,binaryNums.length-1,findNum);
        System.out.println("元素的位置是："+(findResult+1));
    }


}
