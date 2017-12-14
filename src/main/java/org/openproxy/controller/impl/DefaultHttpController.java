/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openproxy.controller.impl;

import static io.netty.buffer.Unpooled.copiedBuffer;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import java.net.InetSocketAddress;
import javax.ws.rs.core.MediaType;
import org.openproxy.controller.ProxyController;

/**
 *
 * @author dnikiforov
 */
public class DefaultHttpController implements ProxyController {

	@Override
	public HttpResponse resolveClientRequest(HttpRequest request) {
		final String responseMessage = "Default response";

		FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK,
				copiedBuffer(responseMessage.getBytes())
		);

		if (HttpUtil.isKeepAlive(request)) {
			response.headers().set(
					HttpHeaderNames.CONNECTION,
					HttpHeaderValues.KEEP_ALIVE
			);
		}
		response.headers().set(HttpHeaderNames.CONTENT_TYPE,
				MediaType.TEXT_PLAIN);
		response.headers().set(HttpHeaderNames.CONTENT_LENGTH,
				responseMessage.length());

		return response;
	}

	@Override
	public InetSocketAddress adviseTargetAddress() {
		return null;
	}

}
