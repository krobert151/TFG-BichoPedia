import 'package:bicho_pedia/page/specie_details.dart';
import 'package:bicho_pedia/species/model/specie_response.dart';
import 'package:flutter/material.dart';
import 'package:gap/gap.dart';

class SpeciesItem extends StatefulWidget {
  final SpeciesResponse specieResponse;

  const SpeciesItem({super.key, required this.specieResponse});

  @override
  State<SpeciesItem> createState() => _SpeciesItemState();
}

class _SpeciesItemState extends State<SpeciesItem> {
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
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                alignment: Alignment.center,
                child: Container(
                  alignment: Alignment.center,
                  height: 160,
                  width: 160,
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(10)),
                  ),
                  child: ClipRRect(
                    borderRadius: const BorderRadius.all(Radius.circular(10)),
                    child: Image(
                      height: 160,
                      width: 160,
                      fit: BoxFit.cover,
                      image: NetworkImage(
                          'http://10.0.2.2:8080/download/${widget.specieResponse.url!}'),
                    ),
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
              Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  TextButton(
                    style: TextButton.styleFrom(padding: EdgeInsets.all(0)),
                    child: const Row(
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
                            builder: (context) => SpecieDetailsPage(
                                id: widget.specieResponse.id!),
                          ));
                    },
                  ),
                  Container(
                    height: 30,
                    width: 30,
                    decoration: BoxDecoration(
                        image: DecorationImage(
                            fit: BoxFit.fill,
                            image: AssetImage(
                                'assets/${widget.specieResponse.type}.png'))),
                  )
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
