import 'package:bicho_pedia/page/specie_details.dart';
import 'package:bicho_pedia/species/model/species_simple_response.dart';
import 'package:flutter/material.dart';
import 'package:gap/gap.dart';

class SimpleSpeciesItem extends StatefulWidget {
  final SpeciesSimpleResponse specieResponse;

  const SimpleSpeciesItem({super.key, required this.specieResponse});

  @override
  State<SimpleSpeciesItem> createState() => _SimpleSpeciesItemState();
}

class _SimpleSpeciesItemState extends State<SimpleSpeciesItem> {
  @override
  Widget build(BuildContext context) {
    return Card(
      shadowColor: const Color.fromARGB(43, 255, 255, 255),
      elevation: 10,
      margin: const EdgeInsets.symmetric(horizontal: 8),
      child: Container(
        decoration: const BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(10)),
          color: Color.fromARGB(255, 48, 49, 45),
        ),
        width: 170,
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                height: 160,
                width: 160,
                decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                ),
                child: ClipRRect(
                  borderRadius: const BorderRadius.all(Radius.circular(10)),
                  child: Image(
                    fit: BoxFit.cover,
                    image: NetworkImage(widget.specieResponse.url!),
                  ),
                ),
              ),
              const Gap(5),
              Text(
                textAlign: TextAlign.start,
                widget.specieResponse.scientificName!,
                overflow: TextOverflow.ellipsis,
                style: const TextStyle(
                    fontFamily: 'OpenSans', color: Colors.white),
              ),
              TextButton(
                style: TextButton.styleFrom(padding: EdgeInsets.all(0)),
                child: const Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Icon(Icons.menu, color: Colors.white, size: 15),
                    Text(
                      ' Read',
                      style: TextStyle(
                          fontFamily: 'OpenSans',
                          color: Colors.white,
                          fontSize: 10,
                          fontWeight: FontWeight.w200),
                    )
                  ],
                ),
                onPressed: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) =>
                            SpecieDetailsPage(id: widget.specieResponse.id!),
                      ));
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
