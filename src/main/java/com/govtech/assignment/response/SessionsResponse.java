package com.govtech.assignment.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SessionsResponse implements Serializable {

	private static final long serialVersionUID = -8532913889886180800L;

	private Long total;
	private List<SessionMetaDataResponse> sessions;

}
