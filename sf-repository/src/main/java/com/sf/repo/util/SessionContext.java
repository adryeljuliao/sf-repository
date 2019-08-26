package com.sf.repo.util;

import javax.faces.context.FacesContext;

public class SessionContext {
	
	public static Object getValueObjOnSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
	}

	public static void setValueObjOnSession(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
	}

}
