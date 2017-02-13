/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

/**
 *
 * @author nholvoet
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    
    private final SslContext sslCtx;
  
      public HttpServerInitializer(SslContext sslCtx) {
          this.sslCtx = sslCtx;
      }
    
    @Override 
    public void initChannel(SocketChannel ch) throws Exception { 
        // Create a default pipeline implementation. 
        ChannelPipeline p = ch.pipeline(); 
 
        if (sslCtx != null) {
              p.addLast(sslCtx.newHandler(ch.alloc()));
        }
 
        p.addLast("decoder", new HttpRequestDecoder()); 
        // Uncomment the following line if you don't want to handle HttpChunks. 
        //pipeline.addLast("aggregator", new HttpChunkAggregator(1048576)); 
        p.addLast("encoder", new HttpResponseEncoder()); 
        // Remove the following line if you don't want automatic content compression. 
        p.addLast("deflater", new HttpContentCompressor()); 
        p.addLast("handler", new HttpServerHandler()); 
    } 
}
