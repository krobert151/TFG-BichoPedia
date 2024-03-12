part of 'markers_bloc.dart';

@immutable
sealed class MarkersState {}

final class MarkersInitial extends MarkersState {}

final class MarkersSuccess extends MarkersState {
  final List<MarkersResponse> list;
  MarkersSuccess(this.list);
}

final class MarkersError extends MarkersState {
  final String markers;
  MarkersError(this.markers);
}
