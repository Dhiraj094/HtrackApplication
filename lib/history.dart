import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';
import 'dart:convert';
import 'dart:async';
import './model_class.dart';
import 'user.dart';

class History extends StatefulWidget {
  User myObject;
  History({this.myObject});
  @override
  HistoryState createState() {
    return new HistoryState();
  }
}

class HistoryState extends State<History> {
  List<myModel> myAllData = [];

  @override
  void initState() {
    super.initState();
    loadData();
  }

  loadData() async {
    final url = Uri.parse(
        "http://104.218.120.82:5000/htrack-services/api/healthhistory/list/" +
            widget.myObject.id.toString());
    var response = await http.get(url, headers: {"Aceept": "application/json"});
    print("Url = " + url.toString());
    if (response.statusCode == 200) {
      //print(response.body);
      String responeBody = response.body;
      print(responeBody);
      var jsonBody = json.decode(responeBody);
      for (var data in jsonBody) {
        myAllData.add(new myModel(data['id'], data['oxygen'], data['pulse'],
            data['record'], data['imageDto'], data['userDto']));
      }
      setState(() {});
      myAllData.forEach((someData) =>
          print("Oxygen : ${someData.oxygen} , Pulse : ${someData.pulse}"));
    } else {
      print('Something went wrong');
    }
  }

  DateFormat dateFormat = DateFormat("yyyy-MM-dd");


  Widget bodyData() => DataTable(

      
      columnSpacing: 30,
      columns: <DataColumn>[
        // DataColumn(
        //   label: Text("Date",
        //       style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
        //   // numeric: false,
        // ),
        // DataColumn(
        //   label: Text("Time",
        //       style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
        //   // numeric: false,
        // ),
        DataColumn(
          label: Text("SpO2%",
              style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
              )),
          // numeric: false,
        ),
        DataColumn(
          label: Text("Pulse-rate",
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          // numeric: false,
        ),
      ],
      rows: myAllData
          .map(
            (name) => DataRow(
              cells: [
                // DataCell(
                  
                //   // Text(name.record,
                //   //     style: TextStyle(
                //   //       fontSize: 15,
                //   //     )),
                // ),
                DataCell(
                  Text(name.oxygen.toString(), style: TextStyle(fontSize: 15)),
                ),
                DataCell(
                  Text(name.pulse.toString(), style: TextStyle(fontSize: 15)),
                ),
              ],
            ),
          )
          .toList());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: HexColor("#00c2cb"),
        title: Text("History"),
      ),
      body: myAllData.length == 0
          ? Center(child: Text("Data not available"))
          : bodyData(),
    );
  }
}
