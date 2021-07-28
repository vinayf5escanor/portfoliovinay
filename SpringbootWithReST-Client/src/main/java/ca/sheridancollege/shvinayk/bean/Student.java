package ca.sheridancollege.shvinayk.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NonNull
	private String name;
}
