package ca.sheridancollege.shvinayk.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cast implements Serializable {
	private static final long serialVersionUID = 1L;

	private String Name = "Meliodas";
	private long cp=90000;
	private String sin = "Wrath";
	private String[] sins = { "Lust", "Gluttony", "Greed", "Sloth", "Wrath", "Envy", "Pride" };
	private String[] fields = { "Name", "Combat Power", "Sin" };

}
