package com.demo.main;

import java.io.Serializable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.demo.entity.Teacher;

public class AuditInterceptor extends EmptyInterceptor {

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Teacher) {
			Teacher teacher = (Teacher) entity;
			System.out.println("Teacher saved - ID: " + id + ", Name: " + teacher.getName());
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Teacher) {
			Teacher teacher = (Teacher) entity;
			System.out.println("Teacher updated - ID: " + id + ", Name: " + teacher.getName());
		}
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}
}
