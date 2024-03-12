import 'package:bicho_pedia/species/model/specie_details_response.dart';
import 'package:bicho_pedia/species/model/specie_response.dart';
import 'package:bicho_pedia/species/model/species_name_resposne.dart';
import 'package:bicho_pedia/species/model/species_simple_response.dart';

abstract class SpecieRepository {
  Future<List<SpeciesSimpleResponse>> getDangerSpeciesSimple(
      int count, int page);

  Future<List<SpeciesResponse>> getSpeciesLists(
      int count, int page, String search);

  Future<SpecieDetailsResponse> getSpecieDetailsById(String id);

  Future<List<SpeciesNameResponse>> getAllSpeciesName();
}
