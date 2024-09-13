import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

class AboutUs extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("About Us"),
        backgroundColor: HexColor("#00c2cb"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Container(
          child: Text("We are providing a HealthCare-Tech platform for people who are suffering through illness to track their regular reports and health.",
          style: TextStyle(fontSize: 20),
          ),
        ),
      ),
    );
  }
}
