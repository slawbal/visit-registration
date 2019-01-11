package com.sb.registrationweb.suppliers;

public interface DataSupplier<T, S> {
	T getData(S s);
}
