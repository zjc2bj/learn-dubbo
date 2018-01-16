/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc.protocol.dubbo;


import static junit.framework.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.DemoService;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.DemoServiceImpl;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.NonSerialized;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.RemoteService;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.RemoteServiceImpl;
import com.alibaba.dubbo.rpc.protocol.dubbo.support.Type;
import com.alibaba.dubbo.rpc.proxy.jdk.JdkProxyFactory;
import com.alibaba.dubbo.rpc.service.EchoService;

import junit.framework.Assert;

/**
 * <code>ProxiesTest</code>
 */

public class DubboProtocolTest {
    private Protocol protocol = new DubboProtocol();
    private ProxyFactory proxy = new JdkProxyFactory();
    
    @Test
    public void testDemoProtocol(){
        try {
        		Protocol protocol = new DubboProtocol();
        		ProxyFactory proxy = new JdkProxyFactory();
            
			DemoService service = new DemoServiceImpl();
			protocol.export(proxy.getInvoker(service, DemoService.class, URL.valueOf("dubbo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));
			service = proxy.getProxy(protocol.refer(DemoService.class, URL.valueOf("dubbo://127.0.0.1:9020/" + DemoService.class.getName() + "?codec=exchange")));
			service.getSize(new String[]{"", "", ""});
			
			service.sayHello("world");
//			assertEquals(service.getSize(new String[]{"", "", ""}), 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}