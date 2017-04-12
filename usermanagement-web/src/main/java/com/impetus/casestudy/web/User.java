package com.impetus.casestudy.web;


public class User
{

  private Integer id;

  private String firstName;

  private String lastName;

  private String age;

  private String email;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getAge()
  {
    return age;
  }

  public void setAge(String age)
  {
    this.age = age;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

//  @Override
//  public int hashCode()
//  {
//    final int prime = 31;
//    int result = 1;
//    result = prime * result + (int) (id ^ (id >>> 32));
//    return result;
//  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    User other = (User) obj;
    if (id != other.id) return false;
    return true;
  }

  @Override
  public String toString()
  {
    return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
  }

}