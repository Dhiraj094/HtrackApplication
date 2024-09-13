import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:form_field_validator/form_field_validator.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import './home.dart';
import './signup.dart';
import 'user.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final fieldEmail1 = TextEditingController();
  final fieldPassword1 = TextEditingController();
  void clearText() {
    fieldEmail1.clear();
    fieldPassword1.clear();
  }

  Future<void> sendloginData() async {
    print("Data send");
    var url =
        Uri.parse('http://104.218.120.82:5000/htrack-services/api/user/login');
    final headers = {"Content-type": "application/json"};
    final data = jsonEncode({
      "login": fieldEmail1.text,
      "password": fieldPassword1.text,
    });
    final response = await http.post(url, headers: headers, body: data);
    if (response.statusCode == 200) {
      
      User userObj = User.fromJson(
        json.decode(response.body)
      );
      print(userObj);


      Navigator.push(context, MaterialPageRoute(builder: (_) => HomePage(myObject: userObj)));

      print('Status code: ${response.statusCode}');
    } else {
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(
        content: Text("Your Login ID or Password is incorrect"),
      ));
    }
    print('Status code: ${response.statusCode}');
    print('Body: ${response.body}');
  }

  bool _isObscure = true;
  bool styleOBJ = true;

  changeStyle() {
    setState(() {
      styleOBJ = false;
    });
  }

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  // void validates() {
  //   if (_formKey.currentState.validate()) {
  //     //clearText();
  //     Navigator.push(context, MaterialPageRoute(builder: (_) => HomePage()));
  //     print("Validated");
  //   } else {
  //     print("Not Validated");
  //   }
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      // appBar: AppBar(
      //     title: Text("Login Page"), backgroundColor: Colors.blue),
      body: SingleChildScrollView(
        child: Form(
          key: _formKey,
          //autovalidate: true,
          child: Column(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.only(top: 50.0),
                child: Center(
                  child: Container(
                      padding: EdgeInsets.only(bottom: 30.0),
                      width: 200,
                      height: 150,
                      /*decoration: BoxDecoration(
                          color: Colors.red,
                          borderRadius: BorderRadius.circular(50.0)),*/
                      child: Image.asset('asset/images/login_logo.png')),
                ),
              ),

              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Welcome",
                    style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
                  ),
                ],
              ),

              SizedBox(
                height: 15,
              ),

              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Login to your account",
                    style: TextStyle(
                      fontSize: 23,
                      //color: Colors.grey,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ],
              ),

              Padding(
                padding: const EdgeInsets.only(
                    left: 15.0, right: 15.0, top: 20, bottom: 0),
                //padding: EdgeInsets.symmetric(horizontal: 15),
                child: TextFormField(
                  controller: fieldEmail1,
                  decoration: InputDecoration(
                      // border: OutlineInputBorder(),
                      labelText: 'Login',
                      labelStyle: TextStyle(
                        fontSize: 20,
                        //fontWeight: FontWeight.bold,
                        color: Colors.blueGrey,
                      ),
                      //hintText: 'xyz@gmail.com',

                      focusedBorder: UnderlineInputBorder(
                        borderSide: BorderSide(color: Colors.blue),
                      ),
                      prefixIcon: Icon(Icons.email)),
                  // validator: MultiValidator([
                  //   RequiredValidator(errorText: "* Required"),
                  //   EmailValidator(errorText: "Enter valid email id"),
                  // ])
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                    left: 15.0, right: 15.0, top: 15, bottom: 0),
                //padding: EdgeInsets.symmetric(horizontal: 15),
                child: Container(
                  child: TextFormField(
                      controller: fieldPassword1,
                      obscureText: _isObscure,
                      decoration: InputDecoration(
                        //border: OutlineInputBorder(),
                        labelText: 'Password',
                        labelStyle: TextStyle(
                          fontSize: 20,
                          //fontWeight: FontWeight.bold,
                          color: Colors.blueGrey,
                        ),

                        //hintText: 'Enter secure password',
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.blue),
                        ),
                        prefixIcon: Icon(Icons.lock),
                        suffixIcon: IconButton(
                          icon: Icon(_isObscure
                              ? Icons.visibility
                              : Icons.visibility_off),
                          onPressed: () {
                            setState(() {
                              _isObscure = !_isObscure;
                            });
                          },
                        ),
                      ),
                      validator: MultiValidator([
                        RequiredValidator(errorText: "* Required"),
                        MinLengthValidator(6,
                            errorText:
                                "Password should be atleast 6 characters"),
                        MaxLengthValidator(15,
                            errorText:
                                "Password should not be greater than 15 characters")
                      ])),
                ),
              ),
              FlatButton(
                  onPressed: () {
                    //Forgot Password screen
                  },
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      Text(
                        "Forgot your Password?",
                        style: TextStyle(
                          // color: HexColor("#00c2cb"),
                          // color: Colors.red,
                          fontSize: 15,
                        ),
                      )
                    ],
                  )),
              SizedBox(
                height: 20,
              ),
              Container(
                height: 50,
                width: 250,
                decoration: BoxDecoration(
                  color: Colors.blue,
                  borderRadius: BorderRadius.circular(20),
                ),
                child: FlatButton(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(5)),
                  onPressed: () {
                    // validates();
                    sendloginData();
                  },
                  color: HexColor("#00c2cb"),
                  child: Text(
                    'Login',
                    style: TextStyle(color: Colors.white, fontSize: 25),
                  ),
                ),
              ),
              SizedBox(
                height: 20,
              ),
              //Divider(height: 5, thickness: 2),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Don't have an account?",
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  FlatButton(
                      onPressed: () {
                        Navigator.pushReplacement(context,
                            MaterialPageRoute(builder: (_) => SignupPage()));
                        changeStyle();
                      },
                      child: Row(
                        children: [
                          Text(
                            "Sign up.",
                            // style: TextStyle(
                            //   color: Colors.blue,
                            // fontSize: 20,
                            style: styleOBJ
                                ? TextStyle(
                                    //  color: Colors.red,
                                    color: HexColor("#00c2cb"),
                                    fontSize: 22,
                                    //fontWeight: FontWeight.bold
                                  )
                                : TextStyle(
                                    color: Colors.red,
                                    fontSize: 22,
                                    //fontWeight: FontWeight.bold
                                  ),
                          ),
                        ],
                      )),
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
