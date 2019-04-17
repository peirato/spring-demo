package com.peirato.dome.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis.pool")
public class SpringRedisPoolSettings {

	private int maxidle;
	private int minidle;
	private int maxactive;
	private int maxwait;
	public int getMaxidle() {
		return maxidle;
	}
	public void setMaxidle(int maxidle) {
		this.maxidle = maxidle;
	}
	public int getMinidle() {
		return minidle;
	}
	public void setMinidle(int minidle) {
		this.minidle = minidle;
	}
	public int getMaxactive() {
		return maxactive;
	}
	public void setMaxactive(int maxactive) {
		this.maxactive = maxactive;
	}
	public int getMaxwait() {
		return maxwait;
	}
	public void setMaxwait(int maxwait) {
		this.maxwait = maxwait;
	}
	
	

}
