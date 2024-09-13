import 'dart:convert';
// import 'dart:html';
//import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:form_field_validator/form_field_validator.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:http/http.dart' as http;
// import 'dart:async';
// import 'dart:convert';
import './login.dart';

class SignupPage extends StatefulWidget {
  @override
  _SignupPageState createState() => _SignupPageState();
}

class _SignupPageState extends State<SignupPage> {
  final fieldName = TextEditingController();
  final fieldEmail = TextEditingController();
  final fieldPassword = TextEditingController();
  final fieldGender = TextEditingController();
  final fielddob = TextEditingController();
  final fieldblood = TextEditingController();

  // void clearText() {
  //   fieldName.clear();
  //   fieldEmail.clear();
  //   fieldPassword.clear();
  //   fieldGender.clear();
  //   fielddob.clear();
  //   fieldblood.clear();
  // }

  Future<void> sendData() async {
    print("Data send");
    var url = Uri.parse('http://104.218.120.82:5000/htrack-services/api/user');
    final headers = {"Content-type": "application/json"};
    final data = jsonEncode({
      "name": fieldName.text,
      "login": fieldEmail.text,
      "password": fieldPassword.text,
      "gender": fieldGender.text,
      "birhdate": fielddob.text,
      "bloodGroup": fieldblood.text,
    });
    final response = await http.put(url, headers: headers, body: data);

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

  GlobalKey<FormState> _formkey = GlobalKey<FormState>();

  void validatesignup() {
    if (_formkey.currentState.validate()) {
      //clearText();
      Navigator.push(context, MaterialPageRoute(builder: (_) => LoginPage()));
      print("Validated");
    } else {
      print("Not Validated");
    }
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        // appBar: AppBar(
        //   backgroundColor: Colors.white,
        //   centerTitle: true,
        //   title: Text("SingUp Page"),
        // ),
        body: SingleChildScrollView(
          child: Form(
            key: _formkey,
            child: Column(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.only(top: 25),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Create Account",
                        style: TextStyle(
                            fontSize: 35, fontWeight: FontWeight.bold),
                      ),
                    ],
                  ),
                ),

                // SizedBox(
                //   height: 15,
                // ),

                // Row(
                //   mainAxisAlignment: MainAxisAlignment.center,
                //   children: [
                //     Text(
                //       " SIGNUP",
                //       style: TextStyle(
                //         fontSize: 20,
                //         color: Colors.grey,
                //         fontWeight: FontWeight.bold,
                //       ),
                //     ),
                //   ],
                // ),

                Padding(
                  padding: const EdgeInsets.only(
                      left: 15.0, right: 15.0, top: 20, bottom: 10),
                  //padding: EdgeInsets.symmetric(horizontal: 15),
                  child: TextFormField(
                    controller: fieldName,
                    decoration: InputDecoration(
                        // border: OutlineInputBorder(),
                        labelText: 'Full Name',
                        labelStyle: TextStyle(
                          fontSize: 20,
                          // fontWeight: FontWeight.bold,
                          color: Colors.blueGrey,
                        ),
                        // hintText: 'Enter your name',
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.blue),
                        ),
                        prefixIcon: Icon(Icons.book)),
                    validator: RequiredValidator(errorText: "* Required"),
                  ),
                ),

                Padding(
                  padding: const EdgeInsets.only(
                      left: 15.0, right: 15.0, top: 0, bottom: 10),
                  //padding: EdgeInsets.symmetric(horizontal: 15),
                  child: TextFormField(
                      controller: fieldEmail,
                      decoration: InputDecoration(
                          // border: OutlineInputBorder(),
                          labelText: 'Email',
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
                      validator: MultiValidator([
                        RequiredValidator(errorText: "* Required"),
                        EmailValidator(errorText: "Enter valid email id"),
                      ])),
                ),

                Padding(
                  padding: const EdgeInsets.only(
                      left: 15.0, right: 15.0, top: 0, bottom: 10),
                  //padding: EdgeInsets.symmetric(horizontal: 15),
                  child: Container(
                    child: TextFormField(
                        controller: fieldPassword,
                        obscureText: _isObscure,
                        decoration: InputDecoration(
                          //border: OutlineInputBorder(),
                          labelText: 'Password',
                          labelStyle: TextStyle(
                            fontSize: 20,
                            //fontWeight: FontWeight.bold,
                            color: Colors.blueGrey,
                          ),
                          // hintText: 'Enter secure password',
                          focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Colors.blue,
                              // width: 2,
                            ),
                            // borderRadius: BorderRadius.circular(200),
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

                // FlatButton(
                //     onPressed: () {
                //       //Forgot Password screen
                //     },
                //     child: Row(
                //       mainAxisAlignment: MainAxisAlignment.end,
                //       children: [
                //         Text(
                //           "Forgot your Password?",
                //           style: TextStyle(
                //             color: Colors.blue,
                //             fontSize: 18,
                //           ),
                //         )
                //       ],
                //     )),
                Padding(
                  padding: const EdgeInsets.only(
                      left: 15.0, right: 15.0, top: 0, bottom: 10),
                  //padding: EdgeInsets.symmetric(horizontal: 15),
                  child: TextFormField(
                    controller: fieldGender,
                    decoration: InputDecoration(
                        // border: OutlineInputBorder(),
                        labelText: 'Gender',
                        labelStyle: TextStyle(
                          fontSize: 20,
                          //fontWeight: FontWeight.bold,
                          color: Colors.blueGrey,
                        ),
                        //hintText: 'Male/Female',
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.blue),
                        ),
                        prefixIcon: Icon(Icons.baby_changing_station_rounded)),
                    validator: RequiredValidator(errorText: "* Required"),
                  ),
                ),

                Padding(
                  padding: const EdgeInsets.only(
                      left: 15.0, right: 15.0, top: 0, bottom: 10),
                  //padding: EdgeInsets.symmetric(horizontal: 15),
                  child: TextFormField(
                    controller: fielddob,
                    decoration: InputDecoration(
                        // border: OutlineInputBorder(),
                        labelText: 'Birth Date',
                        labelStyle: TextStyle(
                          fontSize: 20,
                          //fontWeight: FontWeight.bold,
                          color: Colors.blueGrey,
                        ),
                        hintText: 'yyyy-dd-mm',
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.blue),
                        ),
                        prefixIcon: Icon(Icons.date_range)),
                    validator: RequiredValidator(errorText: "* Required"),
                  ),
                ),

                Padding(
                  padding: const EdgeInsets.only(
                      left: 15.0, right: 15.0, top: 0, bottom: 10),
                  //padding: EdgeInsets.symmetric(horizontal: 15),
                  child: TextFormField(
                    controller: fieldblood,
                    decoration: InputDecoration(
                        // border: OutlineInputBorder(),
                        labelText: 'Blood Group',
                        labelStyle: TextStyle(
                          fontSize: 20,
                          //fontWeight: FontWeight.bold,
                          color: Colors.blueGrey,
                        ),
                        //hintText: 'B +ve',
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.blue),
                        ),
                        prefixIcon: Icon(Icons.local_hospital)),
                    validator: RequiredValidator(errorText: "* Required"),
                  ),
                ),
                SizedBox(
                  height: 15,
                ),
                Container(
                  height: 50,
                  width: 250,
                  decoration: BoxDecoration(
                      color: Colors.blue,
                      borderRadius: BorderRadius.circular(20)),
                  child: FlatButton(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5)),
                    onPressed: () async {
                      validatesignup();
                      // await sendData().then((value) {
                      //   print(value);
                      //});
                      //getData();
                      //createAlbum();
                      sendData();
                    },
                    color: HexColor("#00c2cb"),
                    child: Text(
                      'Sign Up',
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
                      "Already have an account?",
                      style:
                          TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                    ),
                    FlatButton(
                        onPressed: () {
                          Navigator.push(context,
                              MaterialPageRoute(builder: (_) => LoginPage()));
                        },
                        child: Row(
                          children: [
                            Text(
                              "Log in.",
                              // style: TextStyle(
                              //   color: Colors.blue,
                              // fontSize: 20,
                              style: styleOBJ
                                  ? TextStyle(
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
      ),
    );
  }
}
