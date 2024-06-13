import 'dart:io';

import 'package:bicho_pedia/encounters/model/encounter_detail_response.dart';
import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/encounters/model/encounter_simple_response.dart';
import 'package:bicho_pedia/encounters/model/file_response.dart';
import 'package:bicho_pedia/encounters/model/markes_response.dart';
import 'package:bicho_pedia/encounters/model/new_encounter_dto.dart';

abstract class EncountersRepository {
  Future<List<EncounterSimpleResponse>> getMostLikedEncounters(
      int count, int page);

  Future<List<EncounterResponse>> getEncounters(
      int count, int page, String search);

  Future<List<MarkersResponse>> getMarkers();

  Future<EncounterDetailsResponse> getEncounterResponse(String id);

  Future<NewEncounterDTO> newEncounter(NewEncounterDTO newEncounterDTO);

  Future<List<FileResponse>> uploadFiles(List<File> files);
}
