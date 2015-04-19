package woolly.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by aman.jamwal on 4/18/15.
 */
@Getter
@Setter
@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  String name;

}
