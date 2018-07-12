package com.au.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.au.entities.Cart;
import com.au.entities.User;
import com.au.repositories.CartRepository;
import com.au.repositories.UserRepository;

@Controller
public class ApplicationController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	CartRepository cartRepo;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@CrossOrigin
	@GetMapping("/addNewUser")
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		return "addNewUser";
	}
	@CrossOrigin
	@CrossOrigin
	@PostMapping("/addNewUser")
	public ResponseEntity<Integer> saveUser(@RequestBody HashMap<String, String> userDetails) {

		System.out.println("Inside Post method");
		if (null != userDetails) {
			try {
				User user = new User();
				if (userDetails.containsKey("userName")) {
					String userName = userDetails.get("userName");
					if (!userName.isEmpty()) {
						user.setUserName(userName);
					} else {
						System.out.println("Request Username parameter empty");
						throw new Exception();
					}
				} else {
					System.out.println("Request object does not contain userName key");
					throw new Exception();
				}
				if (userDetails.containsKey("userPass")) {
					String userPass = userDetails.get("userPass");
					if (!userPass.isEmpty() && userPass.length() >= 6) {
						user.setUserPass(userPass);
					} else {
						System.out.println("Password issue in request key userPass");
						throw new Exception();
					}
				} else {
					System.out.println("Request object does not contain userPass key ");
					throw new Exception();
				}
				if (userDetails.containsKey("usermailId")) {
					String usermailId = userDetails.get("usermailId");
					if (!usermailId.isEmpty() && VALID_EMAIL_ADDRESS_REGEX.matcher(usermailId).find()) {
						user.setUsermailId(usermailId);
					} else {
						System.out.println("Email format not right");
						throw new Exception();
					}
				} else {
					System.out.println("Request object does not contain useremailId key ");
					throw new Exception();
				}
				if (userDetails.containsKey("userContact")) {
					String userContact = userDetails.get("userContact");
					if (!userContact.isEmpty() && userContact.matches("\\d{10}")) {
						user.setUserContact(userContact);
					} else {
						System.out.println("Contact number format not right");
						throw new Exception();
					}
				} else {
					System.out.println("Request object does not contain userContact key ");
					throw new Exception();
				}
				Date date=new Date();
				String cartId=Long.toString(date.getTime());
		    	System.out.println(cartId);
				Cart cart=new Cart();
				cart.setCartId(cartId);
				cart.setMenuId(-1);
				cart.setVenueId(-1);
				cartRepo.save(cart);
				user.setCartId(cartId);
				userRepo.save(user);
				return new ResponseEntity<Integer>(1, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("The request object is null");
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/anotherlogin")
	public ResponseEntity<Integer> login(@RequestBody HashMap<String, String> credMap) {
		List<User> users = userRepo.findAll();
		for (User u : users) {
			if (u.getUsermailId().equals(credMap.get("email"))) {
				if (u.getUserPass().equals(credMap.get("password"))) {
					return new ResponseEntity<Integer>(u.getUserId(), HttpStatus.OK);
				} else
					return new ResponseEntity<Integer>(0, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
	}

	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<Integer> anotherlogin(@RequestBody HashMap<String, String> credMap) {
		if(null!=credMap) {
			try{
				if(credMap.containsKey("email")) {
					String emailId=credMap.get("email");
					if(!emailId.isEmpty()){
						User user = userRepo.findByMailId(emailId);
						if (user != null && user.getDelFlag()==0) {
							if(credMap.containsKey("password")) {
								String password=credMap.get("password");
								if(!password.isEmpty()){
									if (user.getUserPass().equals(password)) { 
										System.out.println("Authentication done");
										return new ResponseEntity<Integer>(user.getUserId(), HttpStatus.OK);
									}
									else {
										System.out.println("Authentication failed");
										return new ResponseEntity<Integer>(0, HttpStatus.NOT_ACCEPTABLE);
										}
								}
								else {
									System.out.println("Password empty");
									throw new Exception();
								}
							}
							else {
								System.out.println("Request object does not contain password");
								throw new Exception();
							}
						}
						else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<Integer>(-2,HttpStatus.INTERNAL_SERVER_ERROR);							
						}
					}
					else {
						System.out.println("Email id is empty in request object");
						throw new Exception();
					}
				}
				else {
					System.out.println("Request object does not contain email id parameter");
					throw new Exception();
				}
			}catch(Exception e){
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		}
		else {
			System.out.println("Request object is null");
			return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepo.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("/cart")
	public ResponseEntity<User> getUserByCart(@RequestBody HashMap<String, String> map) {
		if (null != map) {
			try {
				if (map.containsKey("cartId")) {
					String cartId = map.get("cartId");
					if (!cartId.isEmpty()) {
						System.out.println("Fetching user by cartId");
						User user = userRepo.findUserByCartId(cartId);
						if (null != user && user.getDelFlag() == 0) {
							System.out.println("Fetched");
							return new ResponseEntity<User>(user, HttpStatus.OK);
						} else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("Empty cartId parameter in request object");
						throw new Exception();
					}
				} else {
					System.out.println("request Object does not contain cartid parameter");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Null request object");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

	}

	@CrossOrigin
	@PostMapping("/getById")
	public ResponseEntity<User> getUserById(@RequestBody HashMap<String, String> map) {
		if (null != map) {
			try {
				if (map.containsKey("userId")) {
					int userId = Integer.parseInt(map.get("userId"));
					if (userId > 0) {
						User user = userRepo.findById(userId).get();
						if (null != user && user.getDelFlag() == 0) {
							System.out.println("user fetched");
							return new ResponseEntity<User>(user, HttpStatus.OK);
						} else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("Request object contains UserId parameter is empty");
						throw new Exception();
					}
				} else {
					System.out.println("Request Object does not contain userId");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}

		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/deleteuser")
	public ResponseEntity<Integer> deleteUser(@RequestBody HashMap<String, String> map){
		if(null!=map) {
			try{
				if(map.containsKey("userId")) {
				int userId=Integer.parseInt(map.get("userId"));
				if(userId>0) {
					User user = userRepo.findById(userId).get();
					
					if(null!=user && user.getDelFlag()==0) {
						user.setDelFlag(1);
						userRepo.save(user);
						return new ResponseEntity<Integer>(1, HttpStatus.OK);
					}
					else {
						System.out.println("Query returned an empty set");
						return new ResponseEntity<Integer>(-2,HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				else {
					System.out.println("Request object has empty userId parameter");
					throw new Exception();
				}
				}else {
					System.out.println("Request object does not have userID parameter");
					throw new Exception();
				}
			}catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
				}
		}
		else {
			System.out.println("Request object is null");
			return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
		}
		
	}

	@CrossOrigin
	@PostMapping("/addfilters")
	public ResponseEntity<Integer> setFilters(@RequestBody HashMap<String, String> filterMap) {
		System.out.println("Inside setting filters function");
		if (null != filterMap) {
			String expectedPattern = "yyyy-MM-dd";
			SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
			Date date;
			double estBudget, noOfDays;
			int userId, noOfGuest;
			User user;
			try {
				if (filterMap.containsKey("userId")) {
					userId = Integer.parseInt(filterMap.get("userId"));
					if (userId != 0) {
						user = userRepo.findById(userId).get();
						if (null != user && user.getDelFlag() == 0) {
							System.out.println("User fetched " + userId);
							if (filterMap.containsKey("weddingDate")) {
								date = formatter.parse(filterMap.get("weddingDate"));
								if (date != null) {
									user.setWeddingDate(date);
									System.out.println(date);
								} else {
									System.out.println("Request object has empty wedding Date for user " + userId);
									throw new Exception();
								}
							} else {
								System.out.println(
										"Request object does not contain weddingDate parameter for user" + userId);
								throw new Exception();
							}

							if (filterMap.containsKey("estBudget")) {
								estBudget = Double.parseDouble(filterMap.get("estBudget"));
								if (estBudget > 0) {
									user.setEstBudget(estBudget);
								} else {
									System.out.println("Estimated budget should be greater than 0");
									throw new Exception();
								}
							} else {
								System.out.println("Request object does not contain estBudget parameter");
								throw new Exception();
							}

							if (filterMap.containsKey("noOfDays")) {
								noOfDays = Double.parseDouble(filterMap.get("noOfDays"));
								if (noOfDays > 0) {
									user.setNoOfWeddingDays(noOfDays);
								} else {
									System.out.println("number of days should be greater than 0");
									throw new Exception();
								}
							} else {
								System.out.println("Request object does not contain noOfDays parameter");
								throw new Exception();
							}
							if (filterMap.containsKey("noOfGuest")) {
								noOfGuest = Integer.parseInt(filterMap.get("noOfGuest"));
								if (noOfGuest > 0) {
									user.setNoOfGuest(noOfGuest);
								} else {
									System.out.println("Request object has empty no of guests for user " + userId);
									throw new Exception();
								}
							} else {
								System.out.println(
										"Request object does not contain noOfGuest parameter for user" + userId);
								throw new Exception();
							}
							userRepo.save(user);
							return new ResponseEntity<Integer>(1, HttpStatus.OK);
						} else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}

					else {
						System.out.println("Request object has empty userId parameter");
						throw new Exception();
					}

				} else {
					System.out.println("Request object does not contain userId");
					throw new Exception();
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-3, HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

}
