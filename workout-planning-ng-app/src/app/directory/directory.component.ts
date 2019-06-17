import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatTableDataSource, MatSort} from '@angular/material';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router} from '@angular/router';
import {ApiService} from '../shared/api.service';
import {AuthorizationService} from '../authorization/authorization.service';
import {Exercise} from '../shared/model/exercise';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-directory',
  templateUrl: './directory.component.html',
  styleUrls: ['./directory.component.css'],
  animations: [
    trigger('detailExpand', [
      state('void', style({height: '0px', minHeight: '0', visibility: 'hidden'})),
      state('*', style({height: '*', visibility: 'visible'})),
      transition('void <=> *', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class DirectoryComponent implements OnInit {
  exercises: Exercise[];
  displayedColumns: string[] = ['name'];
  dataSource = new MatTableDataSource<Exercise>(this.exercises);

  displayedStyle = 'card';

  muscleLoad: string[] = ['hips', 'biceps', 'abs', 'chest', 'shoulders', 'back'];
  groupedBy: string = null;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router, private authService: AuthorizationService,
              private apiService: ApiService) {
  }

  isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

  ngOnInit() {
    // this.exercises = this.loadExercises();
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    /*Just for example*/
    this.exercises = [
      {
        id: 'id1',
        name: 'name',
        description: {technique: 'description1', features: ''},
        measureList: ['measure'],
        infForRecommendation: {
          complexity: 0.55,
          muscleLoad: new Map([
            ['hips', 0.3],
            ['biceps', 0.3],
            ['abs', 0.3],
            ['chest', 0.5],
            ['shoulders', 0.3],
            ['back', 0.3]
          ]),
        }
      },
      {
        id: 'id2',
        name: 'name2',
        description: {technique: 'description2', features: ''},
        measureList: [''],
        infForRecommendation: {
          complexity: 0.55,
          muscleLoad: new Map([
            ['hips', 0.3],
            ['biceps', 0.5],
            ['abs', 0.3],
            ['chest', 0.3],
            ['shoulders', 0.3],
            ['back', 0.3]
          ]),
        }
      },
      {
        id: 'id3',
        name: 'name3',
        description: {technique: 'description3', features: ''},
        measureList: [''],
        infForRecommendation: {
          complexity: 0.55,
          muscleLoad: new Map([
            ['hips', 0.5],
            ['biceps', 0.3],
            ['abs', 0.3],
            ['chest', 0.3],
            ['shoulders', 0.3],
            ['back', 0.3]
          ]),
        }
      }];
    this.dataSource.data = this.exercises;
  }

  loadExercises(): Exercise[] {
    this.apiService.getAllExercises().subscribe(
      result => {
        return result;
      },
      error1 => {
        console.log(error1);
      }
    );
    return [];
  }

  applyFilter(filterValue: string): void {
    this.dataSource.filterPredicate =
      (data: Exercise, filter: string) => {
        return (this.groupedBy === null && data.name.indexOf(filter) !== -1) ||
          (this.groupedBy !== null &&
            data.infForRecommendation.muscleLoad.get(this.groupedBy) > 0.4 &&
            data.name.indexOf(filter) !== -1);
    };
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    if (filterValue !== '') {
      this.dataSource.filter = filterValue;
    }
  }

  applyGroupFilter(filterValue: string): void {
    this.groupedBy = filterValue;

    this.dataSource.filterPredicate =
      (data: Exercise, filter: string) => {
        return data.infForRecommendation.muscleLoad.get(filter) > 0.4;
      };

    this.dataSource.filter = filterValue;
    this.displayedStyle = 'table';
  }

  switchToCard(): void {
    this.displayedStyle = 'card';
    this.groupedBy = null
  }

  switchToTable(): void {
    this.displayedStyle = 'table';
    this.groupedBy = null;
    this.dataSource.filter = '';
  }
}
