package ca.sheridancollege.shvinayk.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private Long id;
	private String name;
	private String course;
	private Double mark;
}
