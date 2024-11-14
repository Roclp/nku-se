package net;

import java.io.Serializable;

public class BaseUser implements Serializable{

	public int BaseInt=0;
	public String toString() {
		return "BaseUser"+BaseInt+"";
	}
}
