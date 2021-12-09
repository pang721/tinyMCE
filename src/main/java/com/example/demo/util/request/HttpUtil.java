package com.example.demo.util.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.expression.ParseException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class HttpUtil {

	private static Logger logger = Logger.getLogger("HttpUtil");


	public static String sendpost(String url, JSONObject jsonObject, String encoding) throws ParseException, IOException{
		String body = "";

		//创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		//setConnectTimeout：设置连接超时时间，单位毫秒。
		//
		//setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
		//
		//setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(2000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(8000).build();
		httpPost.setConfig(requestConfig);
		//装填参数
		StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
		s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		//设置参数到请求对象中
		httpPost.setEntity(s);
//		System.out.println("请求地址："+url);
//        System.out.println("请求参数："+nvps.toString());

		//设置header信息
		//指定报文头【Content-type】、【User-Agent】
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		//获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		//释放链接
		response.close();
		return body;
	}




	public static String sendpostAndHeader(String url, Map<String,String> maps, JSONObject jsonObject, String encoding) throws ParseException, IOException{
		String body = "";

		//创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		//装填参数
		StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
		s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		//设置参数到请求对象中
		httpPost.setEntity(s);
		for(Map.Entry<String,String> entry:maps.entrySet()){
			httpPost.setHeader(entry.getKey(),entry.getValue());
		}
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		//获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		//释放链接
		response.close();
		return body;
	}


	public static String sendpostSms(String httpUrl, String param)
	{
		String result = null;//创建字符串接收响应数据
		try
		{
			URL url = new URL(httpUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			//设置参数
			httpConn.setDoOutput(true);   //需要输出
			httpConn.setDoInput(true);   //需要输入
			httpConn.setUseCaches(false);  //不允许缓存
			httpConn.setRequestMethod("POST");   //设置POST方式连接
			//设置请求属性
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");
			httpConn.connect();
			//建立输入流，向指向的URL传入参数
			DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();

			//获得响应状态
			int resultCode = httpConn.getResponseCode();
			if (resultCode == 200)//当状态码为200即服务器成功响应时，从httpConn.getInputStream()取数据
			{
				StringBuffer sb = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null)
				{
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				return (sb.toString());
			}
			else{//当状态码为非200即服务器成功响应时，从httpConn.getErrorStream()取数据
				StringBuffer sb = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getErrorStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null)
				{
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				return (sb.toString());
			}

		}
		catch (ConnectException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
	}


	public static String getUrlPageAdvance(String url, String method, Map<String, Object> headmap,
										   Map<String, Object> params) {
		HttpURLConnection conn = null;
		StringBuilder content = new StringBuilder(200);
		try {
			BufferedReader br = null;
			URL url1 = new URL(url);
			conn = (HttpURLConnection) url1.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(20000);
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestMethod(method);
			if ("POST".equals(method)) {
				conn.setUseCaches(false);
				conn.setInstanceFollowRedirects(true);
			}
			String pstr = "";
			if(headmap == null){
				headmap = new HashMap<String,Object>();
			}


			if (headmap != null) {
				for (String key : headmap.keySet()) {
					Object headobj = headmap.get(key);
					String headervalue = "";
					if (headobj != null) {
						headervalue = headobj.toString();
					}
					conn.setRequestProperty(key, headervalue);
				}
			}

			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {
					Object valueobj = params.get(key);
					if (valueobj != null) {
						if (valueobj instanceof String[]) {
							String[] values = (String[]) valueobj;
							for (int i = 0; i < values.length; i++) {
								String value = values[i];
								if (!StringUtils.isEmpty(value)) {
									value = URLEncoder.encode(value, "utf-8");
								}
								pstr += key + "=" + value + "&";
							}
						} else {
							String value = valueobj.toString();
							if (!StringUtils.isEmpty(value)) {
								value = URLEncoder.encode(value, "utf-8");
							}
							pstr += key + "=" + value + "&";
						}
					}
				}
				// 去掉最后一个空格
				pstr = pstr.substring(0, pstr.length() - 1);
			}
			conn.connect();
			if ((pstr != null) && (!"".equals(pstr))) {
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(pstr);
				out.flush();
				out.close();
			}

			int rescode = conn.getResponseCode();
			logger.info("[URL请求]:url:" + url + ",参数：" + JSON.toJSONString(params));
			if (rescode == 200) {
				InputStream in = conn.getInputStream();
				String incontent = inpurStreamToString(in);
				logger.info("[URL请求成功响应]:content:" + incontent);
				return incontent;
			} else {
				InputStream errin = conn.getErrorStream();
				String errcontent = inpurStreamToString(errin);
				logger.info("[URL请求失败响应]:响应码:" + rescode + ",errormessage:" + errcontent);
			}

		} catch (IOException e) {
			logger.info("[URL请求失败]:url:" + url + ",参数：" + JSON.toJSONString(params) + ",error:" + e.getMessage());
			// e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return "";
	}

	private static String inpurStreamToString(InputStream in) {
		StringBuilder content = new StringBuilder(200);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String line = "";
				while ((line = br.readLine()) != null) {
					content.append(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return content.toString();
	}
	
}
