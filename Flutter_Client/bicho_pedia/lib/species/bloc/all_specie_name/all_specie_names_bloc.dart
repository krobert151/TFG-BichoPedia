import 'package:bicho_pedia/species/model/species_name_resposne.dart';
import 'package:bicho_pedia/species/repositories/species_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'all_specie_names_event.dart';
part 'all_specie_names_state.dart';

class AllSpecieNamesBloc
    extends Bloc<AllSpecieNamesEvent, AllSpecieNamesState> {
  final SpecieRepository repo;

  AllSpecieNamesBloc(this.repo) : super(AllSpecieNamesInitial()) {
    on<DoAllSpecieNamesEvent>(_getAllNames);
  }

  void _getAllNames(
      DoAllSpecieNamesEvent event, Emitter<AllSpecieNamesState> emit) async {
    try {
      final response = await repo.getAllSpeciesName();
      emit(AllSpecieNamesSuccess(response));
    } on Exception catch (e) {
      emit(AllSpecieNamesError(e.toString()));
    }
  }
}
