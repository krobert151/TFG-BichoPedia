import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/page/encounter_detail_page.dart';
import 'package:flutter/material.dart';
import 'package:gap/gap.dart';

class EncounterItem extends StatefulWidget {
  final EncounterResponse encounterResponse;

  const EncounterItem({super.key, required this.encounterResponse});

  @override
  State<EncounterItem> createState() => _EncounterItemState();
}

class _EncounterItemState extends State<EncounterItem> {
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
                      image: NetworkImage(widget.encounterResponse.url!),
                    ),
                  ),
                ),
              ),
              const Gap(5),
              Text(
                textAlign: TextAlign.start,
                widget.encounterResponse.scientificName!,
                style: const TextStyle(
                    overflow: TextOverflow.ellipsis,
                    fontFamily: 'OpenSans',
                    color: Colors.white),
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
                              builder: (context) => EncounterDetailsPage(
                                  id: widget.encounterResponse.id!),
                            ));
                      }),
                  Gap(50),
                  Container(
                    height: 30,
                    width: 30,
                    decoration: BoxDecoration(
                        image: DecorationImage(
                            fit: BoxFit.fill,
                            image: AssetImage(
                                'assets/${widget.encounterResponse.type}.png'))),
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
