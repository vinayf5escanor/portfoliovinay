package ca.sheridancollege.shvinayk.beans;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class sds implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<Cast> list = new ArrayList<Cast>();

}
