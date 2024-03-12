import 'package:bicho_pedia/page/encounter_form_page.dart';
import 'package:bicho_pedia/page/encounters_page.dart';
import 'package:bicho_pedia/page/encyclopedia_page.dart';
import 'package:bicho_pedia/page/home_page.dart';
import 'package:bicho_pedia/page/user_page.dart';
import 'package:flutter/material.dart';

class MenuScreen extends StatefulWidget {
  const MenuScreen({super.key});

  @override
  State<MenuScreen> createState() => _MenuScreenState();
}

class _MenuScreenState extends State<MenuScreen> {
  int _selectedIndex = 0;
  bool showFloatingActionButton = false;

  static const List<Widget> _widgetOptions = <Widget>[
    HomePage(),
    EncyClopediaPage(),
    EncountersPage(),
    UserPage(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;

      showFloatingActionButton = index == 2;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(255, 19, 20, 13),
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      floatingActionButton: Visibility(
        visible: showFloatingActionButton,
        child: FloatingActionButton(
          splashColor: const Color.fromARGB(255, 207, 230, 139),
          backgroundColor: const Color.fromARGB(255, 190, 222, 97),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(50),
          ),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => const EncounterFormPage()),
            );
          },
          child: const Icon(Icons.add),
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomNavigationBar(
        showSelectedLabels: false,
        showUnselectedLabels: false,
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            backgroundColor: Color.fromARGB(255, 19, 20, 13),
            activeIcon: Icon(Icons.home, size: 30),
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            backgroundColor: Color.fromARGB(255, 19, 20, 13),
            activeIcon: Icon(Icons.book, size: 30),
            icon: Icon(Icons.book),
            label: 'Species',
          ),
          BottomNavigationBarItem(
            backgroundColor: Color.fromARGB(255, 19, 20, 13),
            activeIcon: Icon(Icons.map, size: 30),
            icon: Icon(Icons.map),
            label: 'Encounters',
          ),
          BottomNavigationBarItem(
            backgroundColor: Color.fromARGB(255, 19, 20, 13),
            activeIcon: Icon(Icons.person, size: 30),
            icon: Icon(Icons.person),
            label: 'My profile',
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: const Color.fromARGB(255, 190, 222, 97),
        unselectedItemColor: const Color.fromARGB(255, 120, 120, 120),
        onTap: _onItemTapped,
      ),
    );
  }
}
