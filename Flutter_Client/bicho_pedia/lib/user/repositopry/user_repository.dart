import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/user/model/user_details_response.dart';
import 'package:bicho_pedia/user/model/user_saved_list_response.dart';

abstract class UserRepository {
  Future<UserDetailsResponse> findUserDetail(String id);

  Future<List<UserSavedListResponse>> getUserSavedList(String id);

  Future<List<EncounterResponse>> getUserEncounters(String id);
}
