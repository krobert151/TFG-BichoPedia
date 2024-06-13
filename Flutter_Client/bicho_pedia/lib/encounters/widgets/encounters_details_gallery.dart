import 'package:flutter/material.dart';

class EncounterDetailsGallery extends StatefulWidget {
  final List<String> media;
  const EncounterDetailsGallery({super.key, required this.media});

  @override
  State<EncounterDetailsGallery> createState() =>
      _EncounterDetailsGalleryState();
}

class _EncounterDetailsGalleryState extends State<EncounterDetailsGallery> {
  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 20),
        child: GridView.builder(
          gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 3,
            crossAxisSpacing: 5,
            mainAxisSpacing: 5,
          ),
          itemCount: widget.media.length,
          itemBuilder: (context, index) {
            return RawMaterialButton(
              child: InkWell(
                child: Ink.image(
                  image: NetworkImage(
                      'http://10.0.2.2:8080/download/${widget.media[index]}'),
                  height: 300,
                  fit: BoxFit.cover,
                ),
              ),
              onPressed: () {},
            );
          },
        ));
  }
}
