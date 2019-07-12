import { Directive, HostBinding, HostListener, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import { Exercise } from "src/app/models/exercise";

@Directive({
  selector: '[appCdkDetailRow]'
})
export class CdkDetailRowDirective {
  private row: Exercise;
  private tRef: TemplateRef<{ $implicit: Exercise }>;
  private opened: boolean;

  @HostBinding('class.expanded')
  get expended(): boolean {
    return this.opened;
  }

  @Input()
  set appCdkDetailRow(value: Exercise) {
    if (value !== this.row) {
      this.row = value;
    }
  }

  @Input('appCdkDetailRowTpl')
  set template(value: TemplateRef<{ $implicit: Exercise }>) {
    if (value !== this.tRef) {
      this.tRef = value;
    }
  }

  constructor(public vcRef: ViewContainerRef) { }

  @HostListener('click')
  onClick(): void {
    this.toggle();
  }

  toggle(): void {
    if (this.opened) {
      this.vcRef.clear();
    } else {
      this.render();
    }
    this.opened = this.vcRef.length > 0;
  }

  private render(): void {
    this.vcRef.clear();
    console.log(this.tRef);
    if (this.tRef && this.row) {
      this.vcRef.createEmbeddedView(this.tRef, { $implicit: this.row });
    }
  }
}

