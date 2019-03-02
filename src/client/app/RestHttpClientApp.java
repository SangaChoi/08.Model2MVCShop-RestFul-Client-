package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		

		//RestHttpClientApp.getUserTest_JsonSimple();
		//RestHttpClientApp.getUserTest_Codehaus();

		//RestHttpClientApp.LoginTest_JsonSimple();
		//RestHttpClientApp.LoginTest_Codehaus();	
		
/////////////////////////////////////////////////////////////////////////////
		
		//RestHttpClientApp.addProduct_JsonSimple();
		
		//RestHttpClientApp.postAddProduct_Codehaus();
		
		//RestHttpClientApp.getListProduct_Codehaus();
		//RestHttpClientApp.postListProduct_Codehaus();
		
		//RestHttpClientApp.getGetProduct_Codehaus();
		
		//RestHttpClientApp.getUpdateProduct_Codehaus();
		//RestHttpClientApp.postUpdateProduct_Codehaus();
		
//////////////////////////////////////////////////////////////////////		
		
		//RestHttpClientApp.getAddPurchase_Codehaus();
		//RestHttpClientApp.postAddPurchase_Codehaus();
		
		RestHttpClientApp.getListPurchase_Codehaus();
		
		//RestHttpClientApp.getGetPurchase_Codehaus();
		
		//RestHttpClientApp.getUpdatePurchase_Codehaus();
		//RestHttpClientApp.postUpdatePurchase_Codehaus();
		
		//RestHttpClientApp.getUpdateTranCodeByProd_Codehaus();
		//RestHttpClientApp.getUpdateTranCode_Codehaus();
		
	}




	public static void getAddPurchase_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/purchase/json/addPurchase/10185/user01";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product returnProduct=objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
		
		System.out.println(returnProduct);	
		
	}


	public static void postAddPurchase_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/purchase/json/addPurchase";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		User user=new User();
		user.setUserId("user01");
		
		Product product=new Product();
		product.setProdNo(10185);
		
		Purchase purchase=new Purchase();
		purchase.setBuyer(user);
		purchase.setDivyAddr("����� ���۱�");
		purchase.setReceiverDate("20181231");
		purchase.setDivyRequest("��û�Ѵ�");
		purchase.setPaymentOption("2");
		purchase.setPurchaseProd(product);
		purchase.setReceiverName("����01");
		purchase.setReceiverPhone("010-4444-5555");
		purchase.setTranCode("1");
		
		ObjectMapper objectMapper01=new ObjectMapper();
		String jsonValue=objectMapper01.writeValueAsString(purchase);
		
		HttpEntity requestHttpEntity=new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server���� ���� data Ȯ��]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);

		ObjectMapper objectMapper=new ObjectMapper();
		Purchase returnPurchase=objectMapper.readValue(jsonObj.toString(),Purchase.class);
		System.out.println(returnPurchase);		
		
	}

	
	public static void getListPurchase_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/purchase/json/listPurchase/user01";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper=new ObjectMapper();
		Search returnSearch=objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		
		System.out.println(returnSearch);	
	}
	
	
	public static void getGetPurchase_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/purchase/json/getPurchase/10243";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase returnPurchase=objectMapper.readValue(jsonobj.toString(), Purchase.class);
		
		System.out.println(returnPurchase);
	}

	
	public static void getUpdatePurchase_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/purchase/json/updatePurchase/10243";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase returnPurchase=objectMapper.readValue(jsonobj.toString(), Purchase.class);
		
		System.out.println(returnPurchase);
		
	}

	
	public static void postUpdatePurchase_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/purchase/json/updatePurchase";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		User user=new User();
		user.setUserId("user01");
		
		Product product=new Product();
		product.setProdNo(10185);
		
		Purchase purchase=new Purchase();
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		purchase.setDivyAddr("������");
		purchase.setReceiverDate("20000101");
		purchase.setDivyRequest("�����ص���");
		purchase.setPaymentOption("1");
		purchase.setReceiverName("����");
		purchase.setReceiverPhone("010-1111-2222");
		purchase.setTranCode("1");
		purchase.setTranNo(10244);
	
		
		ObjectMapper objectMapper01=new ObjectMapper();
		String jsonValue=objectMapper01.writeValueAsString(purchase);
		
		HttpEntity requestHttpEntity=new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server���� ���� data Ȯ��]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);

		ObjectMapper objectMapper=new ObjectMapper();
		Purchase returnPurchase=objectMapper.readValue(jsonObj.toString(),Purchase.class);
		System.out.println(returnPurchase);
	}
	
	
	public static void getUpdateTranCodeByProd_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/purchase/json/updateTranCodeByProd/10201/2";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase returnPurchase=objectMapper.readValue(jsonobj.toString(), Purchase.class);
		
		System.out.println(returnPurchase);
	}
	

	public static void getUpdateTranCode_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/purchase/json/updateTranCode/10240/3";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase returnPurchase=objectMapper.readValue(jsonobj.toString(), Purchase.class);
		
		System.out.println(returnPurchase);
		
	}
///////////////////////////////////////////////////////////////////////////
	public static void addProduct_JsonSimple() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json=new JSONObject();
		json.put("fileName", "���̽�");
		json.put("manuDate", 20180101);
		json.put("price", 8000);
		json.put("prodDetail", "������");
		json.put("prodName", "���̽���");
		
		HttpEntity requestHttpEntity=new StringEntity(json.toString(),"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server���� ���� data Ȯ��]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);
		System.out.println(jsonObj);	
		
	}

	
	public static void postAddProduct_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/product/json/addProduct";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Product product=new Product();
		product.setFileName("������");
		product.setManuDate("20180101");
		product.setPrice(230000);
		product.setProdDetail("�Ͼ��");
		product.setProdName("������");
		
		ObjectMapper objectMapper01=new ObjectMapper();
		String jsonValue=objectMapper01.writeValueAsString(product);
		
		HttpEntity requestHttpEntity=new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server���� ���� data Ȯ��]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);

		ObjectMapper objectMapper=new ObjectMapper();
		Product returnProduct=objectMapper.readValue(jsonObj.toString(),Product.class);
		System.out.println(returnProduct);		
	}


	public static void getListProduct_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/product/json/listProduct";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper=new ObjectMapper();
		Search returnSearch=objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		
		System.out.println(returnSearch);	
	}

	
	public static void postListProduct_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/product/json/listProduct";

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Search search=new Search();
		search.setCurrentPage(1);
		search.setSearchCondition("1");
		search.setSearchKeyword("������");	
		
		ObjectMapper objectMapper01=new ObjectMapper();
		String jsonValue=objectMapper01.writeValueAsString(search);
		
		HttpEntity requestHttpEntity=new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server���� ���� data Ȯ��]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);

		ObjectMapper objectMapper=new ObjectMapper();
		Search returnSearch=objectMapper.readValue(jsonObj.get("search").toString(), Search.class);
		
		System.out.println(returnSearch);		
	}
	
	
	public static void getGetProduct_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10160";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println(product);	
	}
	
	
	public static void getUpdateProduct_Codehaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10160";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println(product);	
	}
	
	
	public static void postUpdateProduct_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://127.0.0.1:8080/product/json/updateProduct";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Product product=new Product();	
		product.setProdNo(10160);
		product.setFileName("����");
		product.setManuDate("20000101");
		product.setPrice(250000);
		product.setProdDetail("������");
		product.setProdName("��������");
		
		ObjectMapper objectMapper01=new ObjectMapper();
		String jsonValue=objectMapper01.writeValueAsString(product);
		
		HttpEntity requestHttpEntity=new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse=httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity=httpResponse.getEntity();
		
		InputStream is=responseHttpEntity.getContent();
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		System.out.println("[Server���� ���� data Ȯ��]");
		String serverData=br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonObj=(JSONObject) JSONValue.parse(serverData);

		ObjectMapper objectMapper=new ObjectMapper();
		Product returnProduct=objectMapper.readValue(jsonObj.toString(),Product.class);
		System.out.println(returnProduct);		
	}

	

//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
}