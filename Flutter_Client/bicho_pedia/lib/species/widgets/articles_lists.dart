import 'package:bicho_pedia/species/model/specie_details_response.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';

class ArticlesLists extends StatefulWidget {
  final List<Article> list;
  const ArticlesLists({super.key, required this.list});

  @override
  State<ArticlesLists> createState() => _ArticlesListsState();
}

class _ArticlesListsState extends State<ArticlesLists> {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: widget.list.length,
      itemBuilder: (context, index) {
        return Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 4.0),
              child: Text(
                widget.list[index].title!,
                style: const TextStyle(
                    color: Colors.white, fontFamily: 'OpenSans', fontSize: 15),
              ),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 4.0),
              child: Text(
                widget.list[index].description!,
                style: const TextStyle(
                    color: Colors.white, fontFamily: 'OpenSans', fontSize: 10),
              ),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 4.0),
              child: CarouselSlider.builder(
                  itemCount: widget.list[index].archives?.length,
                  itemBuilder: (context, index2, realIndex) {
                    return Container(
                      decoration: BoxDecoration(
                          borderRadius:
                              const BorderRadius.all(Radius.circular(17)),
                          image: DecorationImage(
                              fit: BoxFit.cover,
                              image: NetworkImage(
                                  'http://10.0.2.2:8080/download/${widget.list[index].archives![index2]}'))),
                    );
                  },
                  options: CarouselOptions(
                    autoPlay: false,
                    enlargeCenterPage: true,
                    viewportFraction: 1,
                    aspectRatio: 2.0,
                    initialPage: 2,
                  )),
            )
          ],
        );
      },
    );
  }
}
