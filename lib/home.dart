import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:htrack/model_class.dart';
import 'package:htrack/user.dart';
import 'dart:convert';
import './history.dart';

import 'package:image_picker/image_picker.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'package:http/http.dart' as http;
import 'dart:io';

import 'drawer.dart';

class HomePage extends StatefulWidget {
  User myObject;
  HomePage({this.myObject});

  Future<void> sendHealth() async {
    print("Health send");
    var url = Uri.parse(
        'http://104.218.120.82:5000/htrack-services/api/healthhistory');
    final headers = {"Content-type": "application/json"};
    final userDto = jsonEncode({
      "id": myObject.id,
    });
    final data = jsonEncode({
      "oxygen": fieldOxygen.text,
      "pulse": fieldPulse.text,
      "userDto": {"id": myObject.id},
      "imageDto" : null,
    });
    print(data);
    final response = await http.put(url, headers: headers, body: data);

    print('Status code: ${response.statusCode}');
    print('Body: ${response.body}');
  }

  @override
  _HomePageState createState() => _HomePageState();
}

final fieldOxygen = TextEditingController();
final fieldPulse = TextEditingController();

class _HomePageState extends State<HomePage> {
  var url =
      Uri.parse('http://104.218.120.82:5000/htrack-services/api/user/login');

  File imageFile;
  int _selectedIndex = 0;
  static const List<Widget> _widgetOptions = <Widget>[
    Text('Home Page',
        style: TextStyle(fontSize: 35, fontWeight: FontWeight.bold)),
    Text('Search Page',
        style: TextStyle(fontSize: 35, fontWeight: FontWeight.bold)),
    Text('Profile Page',
        style: TextStyle(fontSize: 35, fontWeight: FontWeight.bold)),
  ];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        drawer: DrawerPage(),
        appBar: AppBar(
          backgroundColor: HexColor("#00c2cb"),

          title: Text(
            "HEALTH TRACKER",
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          centerTitle: true,
          //automaticallyImplyLeading: false,
          actions: <Widget>[
            Padding(
              padding: EdgeInsets.only(right: 15),
              child: GestureDetector(
                onTap: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (_) => History(
                                myObject: widget.myObject,
                              )));
                },
                child: Icon(
                  Icons.history,
                  size: 26.0,
                ),
              ),
            ),
          ],
        ),
        // bottomNavigationBar: BottomNavigationBar(
        //   type: BottomNavigationBarType.fixed,
        //    //backgroundColor: HexColor("#00c2cb"),
        //   selectedItemColor:  HexColor("#00c2cb"),
        //   //unselectedItemColor: Colors.white.withOpacity(.60),
        //   selectedFontSize: 14,
        //   currentIndex: _selectedIndex,
        //   unselectedFontSize: 14,
        //   onTap: (index) {
        //   setState(() {
        //     _selectedIndex = index;
        //   });
        //   },
        //   items: [
        //     BottomNavigationBarItem(
        //       title: Text('Home'),
        //       icon: Icon(Icons.favorite),
        //     ),
        //     BottomNavigationBarItem(
        //         title: Text('History'), icon: Icon(Icons.history)),
        //     BottomNavigationBarItem(
        //       title: Text("More"),
        //       icon: Icon(Icons.more),
        //     ),
        //   ],
        // ),
        body: ListView(
          children: <Widget>[
            Container(
              //margin: EdgeInsets.only(bottom: 10, top: 10),
              decoration: BoxDecoration(
                color: HexColor("#ADF2F7"),
                //borderRadius: BorderRadius.circular(10),
              ),
              //color: Colors.purple,
              // width: 380,
              height: 100,

              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Row(mainAxisAlignment: MainAxisAlignment.center, children: [
                    Text(
                      "Welcome ${widget.myObject.name}",
                      //textAlign: TextAlign.center,
                      style: TextStyle(
                        color: Colors.black,
                        // color: HexColor("#00c2cb"),
                        fontSize: 19,
                        //fontWeight: FontWeight.bold
                      ),
                    ),
                  ]),
                  Padding(
                    padding: const EdgeInsets.only(top: 2),
                    child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text(
                            "Hope you are doing good today!",
                            //textAlign: TextAlign.center,
                            style: TextStyle(
                              color: Colors.black,
                              //fontFamily: '',
                              //color: HexColor("#00c2cb"),
                              fontSize: 19,
                              //fontWeight: FontWeight.bold
                            ),
                          ),
                        ]),
                  ),
                ],
              ),
            ),
            CarouselSlider(
              options: CarouselOptions(
                height: 180,
                //enlargeCenterPage: true,
                autoPlay: true,
                aspectRatio: 16 / 9,
                autoPlayCurve: Curves.fastOutSlowIn,
                enableInfiniteScroll: true,
                autoPlayAnimationDuration: Duration(milliseconds: 500),
                viewportFraction: 1,
              ),
              items: [
                Container(
                  decoration: BoxDecoration(
                    //borderRadius: BorderRadius.circular(10.0),
                    image: DecorationImage(
                      image: AssetImage('asset/images/h1.jpeg'),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                Container(
                  decoration: BoxDecoration(
                    //borderRadius: BorderRadius.circular(10.0),
                    image: DecorationImage(
                      image: AssetImage('asset/images/h2.jpeg'),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                Container(
                  decoration: BoxDecoration(
                    //borderRadius: BorderRadius.circular(10.0),
                    image: DecorationImage(
                      image: AssetImage('asset/images/h3.jpeg'),
                      fit: BoxFit.cover,
                    ),
                  ),
                )
              ],
            ),

            Container(
              child: Column(
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 15.0, right: 15.0, top: 20, bottom: 10),
                    //padding: EdgeInsets.symmetric(horizontal: 15),
                    child: TextFormField(
                      controller: fieldOxygen,
                      decoration: InputDecoration(
                          // border: OutlineInputBorder(),
                          labelText: 'Oxygen',
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
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                        left: 15.0, right: 15.0, top: 20, bottom: 10),
                    //padding: EdgeInsets.symmetric(horizontal: 15),
                    child: TextFormField(
                      controller: fieldPulse,
                      decoration: InputDecoration(
                          // border: OutlineInputBorder(),
                          labelText: 'Pulse-rate',
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
                    ),
                  ),
                ],
              ),
            ),

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Container(
                  width: 350,
                  height: 100,
                  padding: const EdgeInsets.only(bottom: 10, top: 10),
                  margin: const EdgeInsets.only(top: 13, bottom: 15),
                  child: RaisedButton(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5)),
                    color: HexColor("#ADF2F7"),
                    splashColor: Colors.black,
                    onPressed: () {
                      widget.sendHealth();
                    },
                    child: Row(
                      children: [
                        Padding(
                          padding: const EdgeInsets.only(right: 10, left: 25),
                          child: Icon(Icons.image),
                        ),
                        Expanded(
                          child: Text("UPLOAD DATA",
                              style: TextStyle(
                                  fontSize: 18,
                                  //fontWeight: FontWeight.bold,
                                  color: Colors.black)),
                        ),
                      ],
                    ),
                  ),
                ),
                // Container(
                //   width: 200,
                //   height: 100,
                //   decoration: BoxDecoration(
                //       borderRadius:
                //           BorderRadius.all(Radius.circular(20))),
                //   padding: const EdgeInsets.all(10),
                //   child: RaisedButton(
                //     color: Colors.pink[100],
                //     onPressed: () {
                //       _getFromCamera();
                //     },
                //     child: Text(
                //       "PICK FROM CAMERA",
                //       style: TextStyle(
                //         fontSize: 15,
                //         fontWeight: FontWeight.bold,
                //         color: Colors.black,
                //       ),
                //     ),
                //   ),
                // )
              ],
            ),

            // if (imageFile == null)
            //   Container(
            //     //padding: EdgeInsets.only(top:20),
            //     margin: EdgeInsets.only(top: 20),
            //     height: 100,
            //     width: 400,
            //     decoration: BoxDecoration(
            //         image: DecorationImage(
            //       image: AssetImage("asset/images/hrate1.jpeg"),
            //       fit: BoxFit.cover,
            //     )),
            //   )
            // else
            //   Container(
            //     padding: EdgeInsets.only(bottom: 10),
            //     height: 200,
            //     width: 200,
            //     child: Image.file(imageFile, fit: BoxFit.cover),
            //   )
          ],
        ));
  }

  // Future _imgFromCamera() async {
  //   PickedFile image = await ImagePicker().getImage(
  //     source: ImageSource.camera,
  //     //      maxWidth: 1800,
  //     //     maxHeight: 400,
  //   );

  //   setState(() {
  //     imageFile = File(image.path);
  //   });
  // }

  // _imgFromGallery() async {
  //   PickedFile image = await ImagePicker().getImage(
  //     source: ImageSource.gallery,
  //   );

  //   setState(() {
  //     imageFile = File(image.path);
  //   });
  // }

  // void _showPicker(context) {
  //   showModalBottomSheet(
  //       context: context,
  //       builder: (BuildContext bc) {
  //         return SafeArea(
  //           child: Container(
  //             child: new Wrap(
  //               children: <Widget>[
  //                 new ListTile(
  //                     leading: new Icon(Icons.photo_library),
  //                     title: new Text('Photo Library'),
  //                     onTap: () async {
  //                       await _imgFromGallery();
  //                       Navigator.of(context).pop();
  //                     }),
  //                 new ListTile(
  //                   leading: new Icon(Icons.photo_camera),
  //                   title: new Text('Camera'),
  //                   onTap: () {
  //                     _imgFromCamera();
  //                     Navigator.of(context).pop();
  //                   },
  //                 ),
  //               ],
  //             ),
  //           ),
  //         );
  //       });
  // }

  //   Future<void> sendImage() async {
  //   print("Image send");
  //   var url = Uri.parse('http://192.168.43.225:8080/htrack-services/api/user');
  //   final headers = {"Content-type": "application/json"};
  //   final data =
  //   final response = await http.put(url, headers: headers, body: data);

  //   print('Status code: ${response.statusCode}');
  //   print('Body: ${response.body}');
  // }

}
