package com.backbase.simplemoviesservice.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class OMDBApiResponse {
	
	private String title;
	private String year;
    private String type;
    private String boxOffice;
}
