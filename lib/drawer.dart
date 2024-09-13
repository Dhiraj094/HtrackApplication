import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import './login.dart';
import './aboutus.dart';

class DrawerPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: MediaQuery.of(context).size.width * 0.8,
      child: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              padding: EdgeInsets.only(top: 68, left: 48),
              decoration: BoxDecoration(
                color: HexColor("#00c2cb"),
              ),
              child: Text(
                'HEALTH TRACKER',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 25,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            ListTile(
              title: Text(
                'My Profile',
                style: TextStyle(fontSize: 20),
              ),
              leading: Icon(Icons.person),
            ),
            // ListTile(
            //   title: Text(
            //     'Download Records',
            //     style: TextStyle(fontSize: 20),
            //   ),
            //   leading: Icon(Icons.download_rounded),
              
            // ),
            ListTile(
              title: Text(
                'About Us',
                style: TextStyle(fontSize: 20),
              ),
              leading: Icon(Icons.info),
              onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => AboutUs()));
                },
            ),
            ListTile(
              title: Text(
                'Sign Out',
                style: TextStyle(fontSize: 20),
              ),
              leading: Icon(Icons.logout),
              onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => LoginPage()));
                },
            ),
          ],
        ),
      ),
    );
  }
}
