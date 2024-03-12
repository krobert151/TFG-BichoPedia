import 'package:bicho_pedia/page/login_page.dart';
import 'package:flutter/material.dart';

class OpenApp extends StatelessWidget {
  const OpenApp({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
            height: double.infinity,
            width: double.infinity,
            decoration: const BoxDecoration(
                image: DecorationImage(
                    fit: BoxFit.fill, image: AssetImage('assets/stream.png'))),
            child: Stack(
              children: [
                Container(
                    alignment: Alignment.center,
                    decoration: const BoxDecoration(
                        gradient: LinearGradient(
                            begin: Alignment.topCenter,
                            end: Alignment.bottomCenter,
                            stops: [
                          0.2,
                          0.9
                        ],
                            colors: [
                          Color.fromARGB(0, 0, 0, 0),
                          Color.fromARGB(255, 0, 0, 0)
                        ])),
                    child: const Text(
                      'BichoPedia 2',
                      style: TextStyle(
                          fontFamily: 'OpenSans',
                          color: Color(0xFFFFFFFF),
                          fontSize: 34,
                          fontWeight: FontWeight.bold),
                    )),
                Positioned(
                    right: 5,
                    bottom: 40,
                    child: Container(
                      decoration: const BoxDecoration(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                          gradient: LinearGradient(
                            begin: Alignment.centerLeft,
                            end: Alignment.centerRight,
                            colors: [
                              Color.fromARGB(255, 47, 47, 46),
                              Color.fromARGB(0, 0, 0, 0)
                            ],
                          )),
                      width: 200,
                      child: Padding(
                        padding: const EdgeInsets.all(10.0),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            const Padding(
                              padding: EdgeInsets.all(8.0),
                              child: Text(
                                'Next',
                                style: TextStyle(
                                    fontFamily: 'OpenSans',
                                    fontSize: 20,
                                    fontWeight: FontWeight.w500,
                                    color: Color.fromARGB(255, 255, 255, 255)),
                              ),
                            ),
                            SizedBox(
                              height: 50,
                              width: 50,
                              child: FloatingActionButton(
                                backgroundColor:
                                    const Color.fromARGB(255, 190, 222, 97),
                                child: const Icon(Icons.arrow_back_ios_sharp,
                                    color: Colors.white,
                                    textDirection: TextDirection.rtl),
                                onPressed: () {
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            const LoginPage()),
                                  );
                                },
                              ),
                            )
                          ],
                        ),
                      ),
                    ))
              ],
            )));
  }
}
