package com.troila.lw;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.client.http.HttpRequest;

@RestController
public class MemberController {

	//一個簡單的服務提供者，測試Hystrix的熔斷
	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Member call(@PathVariable Integer id,HttpServletRequest request) {
		Member p = new Member();
		p.setId(id);
		p.setName("dark");
		p.setMessage(request.getRequestURI().toString()+"=="+request.getLocalAddr()+"--"+request.getLocalPort());
		return p;
	}
	
	//整合測試Feign功能的服務提供者
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	//用來處理超時的方法。當處理時長超過500ms時觸發
	@RequestMapping(value = "/toHello", method = RequestMethod.GET)
	public String toHello() throws Exception{
		//為了配合上面500毫秒的觸發條件，因此這裡睡眠一秒
		Thread.sleep(1000);
		return "toHello";
	}
}
